   import java.io.*;
   import java.util.*;
   import java.awt.*;
   import java.awt.event.*;
   import javax.imageio.*;
   import java.net.*;
   import java.lang.*;

    public class Saver
   {
      String	saveInfo;
      String	fileName;
      int		lineCount;
   
   /***Fields from Editor to be extracted***/
      ArrayList<Entity>	entities, performanceEntities, frontEntities, backEntities, backupEntities, backupfrontEntities, backupbackEntities, physProps, staticProps, miscStuff;
      private Entity					currentEntity;
      private Dude					dude;
      private Rectangle				spawnRect;
   /*8*888***8*star*888**/
   
       public Saver(String n)
      {
         fileName = n;
         saveInfo = "";
         lineCount=0;
         
         backEntities=new ArrayList<Entity>();
      }
       public void update(String s)
      {
         saveInfo = s;
         save();
      }
       private void save()
      {
         try
         {
            FileWriter save = new FileWriter(fileName + ".txt");
            BufferedWriter out = new BufferedWriter(save);
            out.write(saveInfo);
            out.close();
         }
             catch (Exception e)
            {}
      
      }
       public void load()
      {
         try
         {
            StringBuilder text = new StringBuilder();
            String NL = System.getProperty("line.separator");
            Scanner scanner = new Scanner(new FileInputStream(fileName + ".txt"));
            try
            {
               while (scanner.hasNextLine())
               {
                  lineCount++;
                  text.append(scanner.nextLine() + NL);
               }
               saveInfo=text.toString();
            }
                catch (Exception e)
               {}
            finally
            {
               scanner.close();
            }
         }
             catch (Exception e)
            {}
      
      }

       public String toString()
      {
         return saveInfo;
      }
   

       public static void main(String[] zomgnigga)
      {
         Saver savez = new Saver("File_1");
         savez.update("StaticEntity Horizontal_Wood_Board____ 11 888.0 521.625 0.2 -0.0010 longwoodboard_horizontal.png");
         savez.load();
         System.out.println(savez.backEntities.get(0).getSaveString());
         System.out.println(savez.toString());
      }
   }

