package over;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Scanner;

public class Readclass {
    public static void init(ServletContext context){
        try{
            Scanner fin=new Scanner(context.getResourceAsStream("WEB-INF/variable"));
            while(fin.hasNext()){
                String className=fin.next();
                int count=fin.nextInt();
                Item item=new Item();
                item.size=count;
                for(int i=0;i<count;i++){
                    String name=fin.next();
                    String type=fin.next();
                    item.key.add(name);
                    item.type.add(type);
                }
                LoginDatabase.Map.put(className,item);
            }
        }catch (Exception e){
            System.err.println("配置文件错误");
            e.printStackTrace();
        }

    }
    //方便sql 语句插入的操作
    public static ArrayList<String> getWord(String table, ArrayList<String> value){
        Item item=LoginDatabase.Map.get(table);
        ArrayList<String> arrayList=new ArrayList<String>();
        for(int i=0;i<item.size;i++){
            String type=item.type.get(i);
            String val=value.get(i);
            if(val.equals("NULL")) {
                arrayList.add("NULL");
            }else if(type.equals("NUM")){
                arrayList.add(sqlfilter.filter(val));
            }else if(type.equals("STRING")){
                arrayList.add("'"+sqlfilter.filter(val)+"'");
            }
        }
        return arrayList;
    }
    public static String getInsertString(String table, ArrayList<String> arrayList){
        int sz = arrayList.size();
        ArrayList<String> ins = getWord(table,arrayList);
        StringBuffer sb = new StringBuffer("insert into ");
        sb.append(table);
        sb.append(" values");
        sb.append(" (");
        for(int i=0;i<sz;i++){
            sb.append(ins.get(i));
            if(i != sz-1) sb.append(",");
        }
        sb.append(")");
        return sb.toString();
    }
}
