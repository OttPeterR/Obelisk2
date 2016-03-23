import java.net.URL;

public class Launcher
	{
		public static void main(String[] argMeMayteesImAPirateYarrrrrrrrrrrrrrr)
			{
				Editor e = new Editor();
				URL url = new Editor().getClass().getResource("Editor.class");
				System.out.println(url);
				System.out.println(convertURL(url.toString()));
				
				
				URL u=null;
		        try
		        {
		           u=new URL(convertURL(url.toString()));
		        }
		            catch(Exception exc){}
				
				
				
				
				e.init(u);
				e.loadEntitiesAndImages();
			}
		public static String convertURL(String in)
			{
				String out="";
				for(int i=0; i<in.length(); i++)
					{
						if(in.substring(i,i+1).equals("/"))
								out=out+"/";
						out=out+in.substring(i,i+1);
					}
				return out;
			}
	}
