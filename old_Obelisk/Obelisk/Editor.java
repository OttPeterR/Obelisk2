   import java.awt.Graphics2D;
   import java.awt.image.BufferedImage;
   import java.awt.*;
   import java.awt.event.*;
   import java.io.File;
   import java.io.FileInputStream;
   import java.io.FileNotFoundException;
   import java.net.URL;
   import java.sql.Date;
   import java.util.*;

   import javax.swing.*;
   import java.awt.Color;

   public class Editor extends JApplet implements MouseListener, MouseMotionListener, KeyListener, Runnable
   {
      private static final long	serialVersionUID	= 5775001651771763725L;
      private int					screenX, screenY, mx, my, idCount, staticPropPos, physPropPos, miscStuffPos, scrollSpeed;
      private int					enemiesKilled;
      private double				fps, timePassed, difference;
      private boolean				physics, inGame, ctrl, shift, displayIDNums, drawHelp;
      private boolean				playerMovingR, playerMovingL, playerSprintingR, playerSprintingL, jump;
      private boolean				scrollUp, scrollDown, scrollLeft, scrollRight;
      private Graphics2D			gfx;
      private Graphics			bg;
      private int					enemiesPos;
      private ArrayList<AI>		enemies;
   
      private Color				editorTextColor;
      private Image				screen, background, errorImage, backbuffer;
      private URL					url;
      private ArrayList<Entity>	entities, performanceEntities, frontEntities, backEntities, backupEntities, backupfrontEntities, backupbackEntities, physProps, staticProps, miscStuff;
      private Entity				currentEntity;
      private Dude				dude;
      private Rectangle			spawnRect;
      private double				spawnRectX, spawnRectY;
   
      private String				saveInfo			= "";
   
   	////////////////////////////////////////////////////////////
   	////////////////Save Name Here//////////////////////////////
   	////////////////////////////////////////////////////////////
   	////////////////////////////////////////////////////////////
   	//	\/	\/	\/	\/	\/	\/	\/	\/	\/	\/	\/	\/	\/	\/
      private String				fileName			= "File_1";
   	//		/\	/\	/\	/\	/\	/\	/\	/\	/\	/\	/\	/\	/\
   	////////////////////////////////////////////////////////////
   	////////////////////////////////////////////////////////////
   	////////////////////////////////////////////////////////////
   	////////////////////////////////////////////////////////////
      int							lineCount			= 0;
   
      public Editor()
      {
         screenX = Toolkit.getDefaultToolkit().getScreenSize().width;
         screenY = Toolkit.getDefaultToolkit().getScreenSize().height;
      }
   
      public Editor(int inx, int iny)
      {
         screenX = Toolkit.getDefaultToolkit().getScreenSize().width;
         screenY = Toolkit.getDefaultToolkit().getScreenSize().height;
      }
   
      public void init(URL lru)
      {
         screenX = Toolkit.getDefaultToolkit().getScreenSize().width;
         screenY = Toolkit.getDefaultToolkit().getScreenSize().height;
      
         url = lru;
         setSize(screenX, screenY);
         addKeyListener(this);
         addMouseListener(this);
         addMouseMotionListener(this);
         fps = 45;
         timePassed = 0;
         difference = 0;
         idCount = 1;
         physics = false; // booleans
         inGame = false;
         displayIDNums = false;
         enemiesKilled = 0;
      
         entities = new ArrayList<Entity>();// actual list of stuff that
      											// is
      											// played //entity stuff
         frontEntities = new ArrayList<Entity>();
         backupEntities = new ArrayList<Entity>();
         backupfrontEntities = new ArrayList<Entity>();
         backEntities = new ArrayList<Entity>();
         backupbackEntities = new ArrayList<Entity>();
         performanceEntities = new ArrayList<Entity>();
         physProps = new ArrayList<Entity>();// a library of available
      											// stuff
         staticProps = new ArrayList<Entity>();// a library of available
      												// stuff
         miscStuff = new ArrayList<Entity>();// use this to put spawn
      											// points,
      											// ammo, and what not
         enemies = new ArrayList<AI>(); //consists of enemy library 
         physPropPos = 0;
         enemiesPos = 0;
      
         physPropPos = 0;
         staticPropPos = 0;
         miscStuffPos = 0;
      
      		//loadEntitiesAndImages();
      
         spawnRectX = 0;
         spawnRectY = 0;
         spawnRect = new Rectangle((int) spawnRectX, (int) spawnRectY, (int) dude.getRectangle().getWidth() + 0, (int) dude.getRectangle().getHeight() + 0);
      
         background = getImage(url, "Black.jpg");
         backbuffer = createImage(screenX, screenY);
         bg = backbuffer.getGraphics();
         bg.drawImage(background, 0, 0, this);
      
         errorImage = getImage(url, "noImage.png");
         currentEntity = staticProps.get(0);
      
      		// screen=getImage(url, "Black.jpg"); //screen
      		// stuff
         screen = createImage(getWidth(), getHeight());
         gfx = (Graphics2D) screen.getGraphics();
         gfx.setColor(Color.CYAN);
         editorTextColor = Color.blue;
         scrollUp = false;
         scrollDown = false;
         scrollLeft = false;
         scrollRight = false;
         scrollSpeed = 20;
      
      }
   
      public void init()
      {
         screenX = Toolkit.getDefaultToolkit().getScreenSize().width;
         screenY = Toolkit.getDefaultToolkit().getScreenSize().height;
      
         url = getDocumentBase();
      		//file:/H:/Files/eclipse/Data/workspace/PROtek/bin/Editor1338870281102.html
      
         setSize(screenX, screenY);
         addKeyListener(this);
         addMouseListener(this);
         addMouseMotionListener(this);
         fps = 45;
         timePassed = 0;
         difference = 0;
         idCount = 1;
         physics = false; // booleans
         inGame = false;
         displayIDNums = false;
         enemiesKilled = 0;
      
         entities = new ArrayList<Entity>();// actual list of stuff that
      											// is
      											// played //entity stuff
         frontEntities = new ArrayList<Entity>();
         backupEntities = new ArrayList<Entity>();
         backupfrontEntities = new ArrayList<Entity>();
         backEntities = new ArrayList<Entity>();
         backupbackEntities = new ArrayList<Entity>();
         performanceEntities = new ArrayList<Entity>();
         physProps = new ArrayList<Entity>();// a library of available
      											// stuff
         staticProps = new ArrayList<Entity>();// a library of available
      												// stuff
         miscStuff = new ArrayList<Entity>();// use this to put spawn
      											// points,
      											// ammo, and what not
         enemies = new ArrayList<AI>(); //consists of enemy library 
         physPropPos = 0;
         enemiesPos = 0;
      
         physPropPos = 0;
         staticPropPos = 0;
         miscStuffPos = 0;
      
         loadEntitiesAndImages();
      
         spawnRectX = 0;
         spawnRectY = 0;
         spawnRect = new Rectangle((int) spawnRectX, (int) spawnRectY, (int) dude.getRectangle().getWidth() + 0, (int) dude.getRectangle().getHeight() + 0);
      
         background = getImage(url, "Black.jpg");
         backbuffer = createImage(screenX, screenY);
         bg = backbuffer.getGraphics();
         bg.drawImage(background, 0, 0, this);
      
         errorImage = getImage(url, "noImage.png");
         currentEntity = staticProps.get(0);
      
      		// screen=getImage(url, "Black.jpg"); //screen
      		// stuff
         screen = createImage(getWidth(), getHeight());
         gfx = (Graphics2D) screen.getGraphics();
         gfx.setColor(Color.CYAN);
         editorTextColor = Color.blue;
         scrollUp = false;
         scrollDown = false;
         scrollLeft = false;
         scrollRight = false;
         scrollSpeed = 20;
      
      }
   
      public URL getDocBase()
      {
         return getDocumentBase();
      }
   
      public void loadEntitiesAndImages() // loads in all the pictures that
      									// are needed
      {
      		// loading Dude images
         Image[] imgs = new Image[15];
      
         imgs[0] = getImage(url, "bodyR.png"); // bodyR
         imgs[1] = getImage(url, "bodyL.png"); // bodyL
         imgs[2] = getImage(url, "stillR.png"); // stillR
         imgs[3] = getImage(url, "stillL.png"); // stillL
         imgs[4] = getImage(url, "runningRight.gif"); // runR
         imgs[5] = getImage(url, "runningLeft.gif"); // runL
         imgs[6] = getImage(url, ""); // midAriR
         imgs[7] = getImage(url, ""); // midAirL
         imgs[8] = getImage(url, "armsAk47Right.png"); // armsRifleR
         imgs[9] = getImage(url, "armsAk47Left.png"); // armsRifleL
         imgs[10] = getImage(url, ""); // armsPistolR
         imgs[11] = getImage(url, ""); // armsPistolL
         imgs[12] = getImage(url, "headR.png"); // headR
         imgs[13] = getImage(url, "headL.png"); // headL
         imgs[14] = getImage(url, "PlayerHitBox.gif");
      
         for (int i = 0; i < 14; i++)
         {
            if (imgs[i] == null)
               imgs[i] = errorImage;
         }
      		// dude= new Dude(imgs , 0, 0, fps, performanceEntities);
         dude = new Dude(imgs, fps, 0, 0, 0, 0, false, false, performanceEntities, 100);
      		////////////////////////////////////////////////////////////
      		////////////////////////////////////////////////////////////
      		////////////////////////////////////////////////////////////
      		////////////////////////////////////////////////////////////
      		// loading Statics here
      		////////////////////////////////////////////////////////////
      		////////////////////////////////////////////////////////////
      		//To add static (non moving) Entities ------ remember to not use spacer, use underscores _
      		//	\/	\/	\/	\/	\/	\/	\/	\/	\/	\/	\/	\/	\/	\/
      		//Image imageName123 = getImage(url, "image_File_Name_Here.extension"));
      		//staticProps.add(new StaticEntity("name_of_Entity", friction as a decimal value, imageName123,  "image_File_Name_Here.extension");
      		//	/\	/\	/\	/\	/\	/\	/\	/\	/\	/\	/\	/\	/\	/\	
      		////////////////////////////////////////////////////////////
      		////////////////////////////////////////////////////////////
         Image concreteFloorThin = getImage(url, "concrete.png");
         staticProps.add(new StaticEntity("Thin_Concrete_Slab", .03, concreteFloorThin, "concrete.png"));
      
         Image DirtBlockPic = getImage(url, "DirtBlockPic.png");
         staticProps.add(new StaticEntity("Dirt_Block", .1, DirtBlockPic, "DirtBlockPic.png"));
      
         Image MetalBlockPic = getImage(url, "MetalBlockPic.png");
         staticProps.add(new StaticEntity("Metal_Block", .1, MetalBlockPic, "MetalBlockPic.png"));
      
         Image redbrick = getImage(url, "redbrick1.png");
         staticProps.add(new StaticEntity("Red_Brick", .1, redbrick, "redbrick1.png"));
      
         Image stair = getImage(url, "stair.png");
         staticProps.add(new StaticEntity("Stair", .1, stair, "stair.png"));
      
         Image cinderblockWall = getImage(url, "cinderblock_wall.png");
         staticProps.add(new StaticEntity("Cinder_Block_Wall", .1, cinderblockWall, "cinderblock_wall.png"));
      
         Image brickWall = getImage(url, "brick_wall.png");
         staticProps.add(new StaticEntity("Brick_Wall", .1, brickWall, "brick_wall.png"));
      
         Image rock1 = getImage(url, "rock1.png");
         staticProps.add(new StaticEntity("Rock_1", .1, rock1, "rock1.png"));
      
         Image rock2 = getImage(url, "rock2.png");
         staticProps.add(new StaticEntity("Rock_2", .1, rock2, "rock2.png"));
      
         Image horizBoard = getImage(url, "longwoodboard_horizontal.png");
         staticProps.add(new StaticEntity("Horizontal_Wood_Board", .1, horizBoard, "longwoodboard_horizontal.png"));
      
         Image longBoardSlanted1 = getImage(url, "longwoodboard_slanted1.png");
         staticProps.add(new StaticEntity("Slanted_Board_up_to_right", .1, longBoardSlanted1, "longwoodboard_slanted1.png"));
      
         Image longBoardSlanted2 = getImage(url, "longwoodboard_slanted2.png");
         staticProps.add(new StaticEntity("Slanted_Board_down_to_right", .1, longBoardSlanted2, "longwoodboard_slanted2.png"));
      
         Image verticalBoard = getImage(url, "longwoodboard_vertical.png");
         staticProps.add(new StaticEntity("Vertical_Wood", .1, verticalBoard, "longwoodboard_vertical.png"));
      
         Image bridgePiece = getImage(url, "bridgestart.png");
         staticProps.add(new StaticEntity("Bridge_Piece", .1, bridgePiece, "bridgestart.png"));
      
         Image ropeFence = getImage(url, "ropefence.png");
         staticProps.add(new StaticEntity("Rope_Fence", .1, ropeFence, "ropefence.png"));
      
         Image pipe = getImage(url, "pipe.png");
         staticProps.add(new StaticEntity("Pipe", .1, pipe, "pipe.png"));
      
         Image pipeJoint = getImage(url, "pipe_joint.png");
         staticProps.add(new StaticEntity("Pipe_Joint_me_breddah,_smoke_it", .1, pipeJoint, "pipe_joint.png"));
      
      		/*
      		 * Image = getImage(url, ""); staticProps.add(new Entity("", 0,
      		 * 0, -1, ));
      		 */
      
      		// loading Physics here my home slice
      
      		// Image miniTroll=getImage(url, "TrollFace.png");
      		// physProps.add(new PhysicsEntity("Bouncing Troll", fps,
      		// miniTroll,
      		// 5000, .1));
      
         Image hippieVan = getImage(url, "hippievan.png");
         physProps.add(new PhysicsEntity("Hippie_Van", fps, hippieVan, "hippievan.png", 10000, .6, 8000));
      
         Image CratePic = getImage(url, "crate100x100.png");
         physProps.add(new PhysicsEntity("Crate", fps, CratePic, "crate100x100.png", 350, .1, 400));
      
         Image trashCan = getImage(url, "trashcan.png");
         physProps.add(new PhysicsEntity("Trash_Can", fps, trashCan, "trashcan.png", 40, .01, 250));
      
         Image pottedPlant = getImage(url, "bush.png");
         physProps.add(new PhysicsEntity("Potted_Plant", fps, pottedPlant, "bush.png", 40, .1, 100));
      
         Image fire1 = getImage(url, "fire.png");
         Particle fireParticle1 = new Particle("Fire", 0, fire1, "fire.png", 10, 0, 0, 2);
      		// physProps.add(fireParticle1);
         physProps.add(new Particle("Fire", 0, fire1, "fire.png", 10, 0, 0, 2));
      
         physProps.add(new ParticleSpawner("Fire_Spawner", 1, fireParticle1, 40, -40, -50, -20, 0));
      
      		/*
      		 * Image = getImage(url, ""); physProps.add(new Entity("", 0, 0,
      		 * -1, ));
      		 */
      		// // // // // // // GOTTA GET DAT ZOMBEEZ
         enemies.add(new Enemy("ZOMB", 14, 0, 0, fps, getImage(url, "zombie.png"), "zombie.png", 200, .3, 25, 10, 300));
      
      		// loading Misc Stuff myah me breddah, true dat
         Image mountains = getImage(url, "mountains.png");
         miscStuff.add(new StaticEntity("Mountains", .1, mountains, "mountains.png"));
      
         Image nightBackground = getImage(url, "nightbackground.png");
         miscStuff.add(new StaticEntity("Night_Sky", .1, nightBackground, "nightbackground.png"));
      
         Image grass = getImage(url, "grass.png");
         miscStuff.add(new StaticEntity("Grass", .1, grass, "grass.png"));
      
         Image table1 = getImage(url, "table.png");
         miscStuff.add(new StaticEntity("Wood_Table", .1, table1, "table.png"));
      
         Image tree1 = getImage(url, "tree1.png");
         miscStuff.add(new StaticEntity("Scraggly_Tree_1", .1, tree1, "tree1.png"));
      
         Image tree1f = getImage(url, "tree1flipped.png");
         miscStuff.add(new StaticEntity("Scraggly_Tree_1_flipped", .1, tree1f, "tree1flipped.png"));
      
         Image tree2 = getImage(url, "tree2.png");
         miscStuff.add(new StaticEntity("Scraggly_Tree_2", .1, tree2, "tree2.png"));
      
         Image tree2f = getImage(url, "tree2flipped.png");
         miscStuff.add(new StaticEntity("Scraggly_Tree_2", .1, tree2f, "tree2flipped.png"));
      
         Image cloud1 = getImage(url, "cloud1.png");
         miscStuff.add(new StaticEntity("Cloud_1", .1, cloud1, "cloud1.png"));
      
         Image cloud2 = getImage(url, "cloud2.png");
         miscStuff.add(new StaticEntity("Cloud_2", .1, cloud2, "cloud2.png"));
      
         Image movingWater = getImage(url, "water.gif");
         miscStuff.add(new StaticEntity("Moving_Water", .05, movingWater, "water.gif"));
      
         Image waterBlock = getImage(url, "waterblock.png");
         miscStuff.add(new StaticEntity("Water_Block", .05, waterBlock, "waterblock.png"));
      
         Image bierdingthing1 = getImage(url, "bierdingthing1.png");
         miscStuff.add(new StaticEntity("bierdingthing1", .1, bierdingthing1, "bierdingthing1.png"));
      
         Image bierdingthing2 = getImage(url, "bierdingthing2.png");
         miscStuff.add(new StaticEntity("bierdingthing2", .1, bierdingthing2, "bierdingthing2.png"));
      
         Image bierdingthing3 = getImage(url, "bierdingthing3.png");
         miscStuff.add(new StaticEntity("bierdingthing3", .1, bierdingthing3, "bierdingthing3.png"));
      
         Image spawner = getImage(url, "spawner.png");
         staticProps.add(new EnemySpawn("EL_SPAWNERO", spawner, "spawner.png", (Enemy) enemies.get(0)));
      }
      
   
      public void run()// limits the fps
      {
         difference = System.nanoTime() - timePassed;
         difference /= 1000000;
         try
         {
            if ((1000 / fps) - difference > 0)
               Thread.sleep((long) ((1000 / fps) - difference));
         }
            catch (Exception e)
            {}
         timePassed = 0 + System.nanoTime();
      }
   
      public void paint(Graphics gg) // puts everything together and makes graphics
      {
         gg.drawImage(backbuffer, 0, 0, this);
         bg.drawImage(background, 0, 0, this);
      
         run();
         simulate((Graphics2D) bg);
      		/*
      		 * for (int i = 0; i < backEntities.size(); i++) { if
      		 * (backEntities.get(i) instanceof EnemySpawn && inGame) { if
      		 * (backEntities.get(i).getRotation() == 0)
      		 * 
      		 * entities.add(((EnemySpawn)backEntities.get(i)).spawn());
      		 * entities.get(i).setName("5");//actually increases enemies
      		 * spawned } }
      		 */
         repaint();
      		/*
      		 * update(gg); run(); gfx = (Graphics2D) gg;
      		 * 
      		 * gfx.drawImage(background, 0, 0, this);
      		 * 
      		 * simulate(gfx); // this the the
      		 * 
      		 * Toolkit.getDefaultToolkit().sync();
      		 * 
      		 * 
      		 * 
      		 * repaint(); //bg.drawImage(backbuffer,0,0,this);
      		 */
      
      }
   
      public void update(Graphics g)
      {
         g.drawImage(backbuffer, 0, 0, this);
      }
   
      public void simulate(Graphics2D g) // update graphics and physics
      {
      
         g.setColor(Color.blue);
      
         if (!inGame) // in-editor stuff
         {
         			// pauseAll();
            if (physics)
            {
            
               resumeAll();
               simulatePhysics();
            }
            drawBackEntities(g);
            drawEntities(g);
            drawFrontEntities(g);
            cameraControl();
         
            g.drawImage(currentEntity.getImage(), mx, my, this);
         
            bg.drawString(difference + "", 200, 200);
            bg.drawString(System.nanoTime() + "", 200, 215);
         }
         if (inGame)
         {
         
            cameraControl();
            simulatePhysics();
            resumeAll();
         
            drawBackEntities(g);
            drawEntities(g);
            processDude(g);
            drawFrontEntities(g);
         }
         drawAllText(g);
         if (dude.getHealth() <= 0)
            goInEditor();
      }
   
      public void simulatePhysics()
      {
      
         for (int i = 0; i < entities.size(); i++)
         {
            if (entities.get(i).getName().equals("ZOMB"))
            {
               Entity ent = entities.get(i);
            
               int xShift = 0;
               int yShift = 0;
               int hitSpeed = 0;
               int dirVar = 0;
               Rectangle dRect = new Rectangle((int) dude.getX() - 20, (int) dude.getY() - 20, (int) dude.getRectangle().getWidth() + 20, (int) dude.getRectangle().getHeight() + 20);
               if (dRect.intersects(ent.getRectangle()))
               {
                  if (dude.getX() < ent.getX())
                     dirVar = -1;
                  if (dude.getX() > ent.getX())
                     dirVar = 1;
                  dude.setxSpeed(dirVar * 1000);
                  dude.setX(dude.getX() + dirVar * 50);
                  hitSpeed = -1 * dirVar * 10000;
                  ent.setXSpeed(1000);
               
                  double plyrdmg = ent.getRotation() + (Math.random() * 6 + 13); // dmg is within 3 of damage
                  boolean crit = (Math.random() * 10 >= 9); // 1 in 10 chance of critical damage
                  if (crit)
                     plyrdmg *= 2; // critical damage is a (for now) 2x multiplier
                  dude.hurt(plyrdmg); // make sure to add these methods in Dude**********************************************
               }
               if (dude.getX() + dude.getPlayerWidth() < ent.getX())
                  xShift = -200; // -5 is speed, will correspond to xSpeed later
               if (dude.getX() > ent.getX() + ent.getRectangle().getWidth())
                  xShift = 200;
               if (dude.getY() + dude.getPlayerHeight() < ent.getY())
                  yShift = -2;
               if (dude.getY() > ent.getY() + ent.getRectangle().getHeight())
                  yShift = 2;
               ent.setXSpeed(xShift + hitSpeed);
            				//	ent.setYSpeed(yShift);
            
            }
         }
      
         for (int i = 0; i < entities.size(); i++)
         {
            for (int j = i + 1; j < entities.size(); j++)
            {
               entities.get(i).checkCollision(entities.get(j));
               entities.get(i).debug(entities.get(j));
            }
            if (entities.get(i).removeMe())
            {
               entities.remove(i);
               i--;
            }
            entities.get(i).applySpeed();
         }
         for (int i = 0; i < backEntities.size(); i++)
         {
            for (int j = i + 1; j < backEntities.size(); j++)
            {
               backEntities.get(i).checkCollision(backEntities.get(j));
               backEntities.get(i).debug(backEntities.get(j));
            }
            if (backEntities.get(i).removeMe())
            {
               backEntities.remove(i);
               i--;
            }
            backEntities.get(i).applySpeed();
            if (backEntities.get(i).getType().equals("EnemySpawn"))
            {
               double x1 = dude.getX();
               double x2 = backEntities.get(i).getX();
               double y1 = dude.getY();
               double y2 = backEntities.get(i).getY();
            
               if (((EnemySpawn) backEntities.get(i)).shouldSpawn() && Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2))) < 1000)
               {
                  Enemy e = (Enemy) enemies.get(0);
                  ((EnemySpawn) backEntities.get(i)).addZamby(fps, new PhysicsEntity(e.getName(), idCount, (int) backEntities.get(i).getX() + 10, (int) backEntities.get(i).getY() + 10, fps, e.getImage(), e.getImageString(), e.getMass(), e.getFriction(), e.getHealth()), entities);
               }
            }
         }
         for (int i = 0; i < frontEntities.size(); i++)
         {
            for (int j = i + 1; j < frontEntities.size(); j++)
            {
               frontEntities.get(i).checkCollision(frontEntities.get(j));
               frontEntities.get(i).debug(frontEntities.get(j));
            }
            if (frontEntities.get(i).removeMe())
            {
               frontEntities.remove(i);
               i--;
            }
            frontEntities.get(i).applySpeed();
         }
      }
   
      public boolean entityInScreen(Entity e)// used for the automatic
      										// occlusion system
      {
      
         double rightx = e.getEditorRectangle().getWidth() + e.getX();
         double bottomy = e.getEditorRectangle().getHeight() + e.getY();
         double x = e.getX();
         double y = e.getY();
         if (x > screenX || y > screenY || rightx < 0 || bottomy < 0)
            return false;
         else
         {
            performanceEntities.add(e);
            return true;
         }
      }
   
      public void processDude(Graphics2D g)
      {
         dude.updateEntities(performanceEntities);
         dude = dude.convert(dude.getX(), dude.getY(), dude.getxSpeed(), dude.getySpeed(), dude.isFalling(), entities, dude.getHealth());
         bg.setColor(Color.red);
         bg.drawString("Health: " + (int) dude.getHealth() + "%", 300, 300);
         playerMovement();
         dude.checkCollisions();
         dude.updateDirection(mx, my);
         dude.render(g);
      }
   
      public void playerMovement()// controls the players movement
      {
         if (inGame)
         {
            if (playerSprintingR && !playerSprintingL)
            {
               dude.sprintRight();
            }
            else if (playerSprintingL && !playerSprintingR)
            {
               dude.sprintLeft();
            }
            else if (playerMovingR && !playerMovingL)
            {
               dude.moveRight();
            }
            else if (playerMovingL && !playerMovingR)
            {
               dude.moveLeft();
            }
            if (jump)
            {
               dude.jump();
            }
         }
      }
   
      public void drawAllText(Graphics2D g)
      {
         Color previous = g.getColor();
         g.setColor(editorTextColor);
         if (!inGame)
         {
            if (drawHelp)
               drawHelp(g, (int) mx, (int) my);
            if (!drawHelp)
               g.drawString("Shift+H for Help", 2, 500);
            if (displayIDNums)
               displayEntityIDNumbers(g);
            displayInfoOnMouseOver(g);
         
            g.drawString("Status: Running", 2, 30);
            g.drawString("PROtek Engine v1.03", 2, 15);
            g.drawString("Current Entity Selection: " + currentEntity.getName() + "", 2, 100);
            g.drawString(("mx=" + mx), 2, 300);
            g.drawString(("my=" + my), 2, 315);
            g.drawString("Local Budget: " + performanceEntities.size(), 2, 250);
            g.drawString("Total Budget: " + (entities.size() + backEntities.size() + frontEntities.size()), 2, 265);
            g.drawRect((int) spawnRect.getX(), (int) spawnRect.getY(), (int) spawnRect.getWidth(), (int) spawnRect.getHeight());
            g.drawString("Spawn Here", (int) spawnRect.getX() + 5, (int) spawnRect.getY() + 15);
         }
         if (inGame)
         {
            dude.drawHUD(g);
         }
         g.setColor(previous);
      }
   
      public void drawHelp(Graphics2D g, int inx, int iny) // draw
      														// instructions
      {
         Color previousColor = g.getColor();
         g.setColor(Color.gray);
         g.fillRect(inx - 2, iny, 700, 300);
         g.fillRect(inx - 2, iny - 21, 100, 14);
         g.setColor(Color.BLUE);
         g.drawString("Shift + H to Close", inx, iny - 10);
      
         g.drawString("I - Next Static Prop", inx, iny + 15);
         g.drawString("K - Last Static Prop", inx + 180, iny + 15);
         g.drawString("A, S, D, W - Pan Camera", inx + 360, iny + 15);
         g.drawString("O - Next Physics Prop", inx, iny + 30);
         g.drawString("L - Last Physics Prop", inx + 180, iny + 30);
         g.drawString("Crtl + S - Save Level", inx + 360, iny + 30);
         g.drawString("P - Next Misc Thing", inx, iny + 45);
         g.drawString("; = Last Mist Thing", inx + 180, iny + 45);
         g.drawString("Shift + S - Set Spawn Point", inx + 360, iny + 45);
         g.drawString("Shift + ", inx + 180, iny + 75);
         g.drawString("Ctrl + ", inx + 360, iny + 75);
         g.drawString("Ctrl + Shift + ", inx + 540, iny + 75);
         g.drawString("B -      ---", inx, iny + 90);
         g.drawString("- Send to Play Layer", inx + 180, iny + 90);
         g.drawString("N - Send to Back", inx, iny + 105);
         g.drawString("- Send to Back Layer", inx + 180, iny + 105);
         g.drawString("M - Send To Front", inx, iny + 120);
         g.drawString("- Send To Front Layer", inx + 180, iny + 120);
      
         g.drawString("Entity Movement", inx, iny + 150);
         g.drawString("Arrow Keys - move 10 pxls", inx, iny + 165);
         g.drawString("-Move imageDimension", inx + 180, iny + 165);
         g.drawString("- Move 1 pxl", inx + 360, iny + 165);
         g.drawString("- Move 1/4 Image Size", inx + 540, iny + 165);
         g.drawString("A, S, D, W - Scroll", inx, iny + 180);
         g.drawString("Player Controls:", inx, iny + 205);
         g.drawString("A, S, D, W - Movement", inx, iny + 220);
         g.drawString(" + Shift", inx + 150, iny + 220);
         g.drawString("Mouse: ", inx, iny + 235);
         g.drawString("Move - Aiming", inx + 100, iny + 235);
         g.drawString("Click - Shoot", inx + 200, iny + 235);
         g.setColor(Color.orange);
         g.drawString("Ctrl + G  -  Switch between Game and Editor mode", inx + 30, iny + 250);
         g.drawString("Shift + S -  Relocate Spawn Rectangle", inx + 30, iny + 265);
         g.drawString("Crtl + S  -  Save Game", inx + 30, iny + 280);
      
         g.setColor(previousColor);
      }
   
      public void displayEntityIDNumbers(Graphics2D g)
      {
         Color previousColor = g.getColor();
         for (int i = 0; i < entities.size(); i++)
         {
            Entity e = entities.get(i);
         
            g.setColor(Color.blue);
         
            String info = "" + e.getID() + " " + e.getName() + "";
            g.drawString(info, (int) e.getX() + 2, (int) e.getY() + 15);
         }
         g.setColor(previousColor);
      }
   
      public void drawEntities(Graphics2D g)
      {
         performanceEntities = new ArrayList<Entity>();// reset the array
         for (int i = 0; i < entities.size(); i++)
         {
            if (entityInScreen(entities.get(i)))
            {
               entities.get(i).renderInEditor(g);
            				//bg.drawImage(entities.get(i).getImage(), (int)entities.get(i).getX(), (int)entities.get(i).getY(), this);
            
            }
            if (entities.get(i).removeMe())
            {
               entities.remove(i);
               i--;
            }
         }
      }
   
      public void drawFrontEntities(Graphics2D g)
      {
         for (int i = 0; i < frontEntities.size(); i++)
         {
            if (entityInScreen(frontEntities.get(i)))
            {
               frontEntities.get(i).renderInEditor(g);
            				//	bg.drawImage(frontEntities.get(i).getImage(), (int)frontEntities.get(i).getX(), (int)frontEntities.get(i).getY(), this);
            
            }
            if (frontEntities.get(i).removeMe())
            {
               frontEntities.remove(i);
               i--;
            }
         
         }
      }
   
      public void drawBackEntities(Graphics2D g)
      {
         for (int i = 0; i < backEntities.size(); i++)
         {
            if (entityInScreen(backEntities.get(i)))
            {
               backEntities.get(i).renderInEditor(g);
            				//bg.drawImage(backEntities.get(i).getImage(), (int)backEntities.get(i).getX(), (int)backEntities.get(i).getY(), this);
            
            }
            if (backEntities.get(i).removeMe())
            {
               backEntities.remove(i);
               i--;
            }
         }
      }
   
      public void translate(double inx, double iny) // Entity interaction
      												// methods
      {
         dude = dude.convert(dude.getX() + inx, dude.getY() + iny, dude.getxSpeed(), dude.getySpeed(), dude.isFalling(), entities, dude.getHealth());
         spawnRectX += inx;
         spawnRectY += iny;
         spawnRect.setLocation((int) spawnRectX, (int) spawnRectY);
      
         for (int i = 0; i < entities.size(); i++)
         {
            entities.get(i).convertMove(entities.get(i).getX() + inx, entities.get(i).getY() + iny);
         }
         for (int i = 0; i < frontEntities.size(); i++)
         {
            frontEntities.get(i).convertMove(frontEntities.get(i).getX() + inx, frontEntities.get(i).getY() + iny);
         }
         for (int i = 0; i < backEntities.size(); i++)
         {
            backEntities.get(i).convertMove(backEntities.get(i).getX() + inx, backEntities.get(i).getY() + iny);
         }
      }
   
      public void cameraControl() // keeps the "camera" where it is needed and
      							// (usually) most helpful
      {
         if (inGame)
         {
            double yCenter = dude.getY();
            double xCenter = dude.getX() + 50;
         
            double yMove = (screenY / 2) - yCenter;
            double xMove = (screenX / 2) - xCenter;
         
            translate(xMove, yMove);
         }
         else if (!inGame)
         {
         			// keyboard scrolling
            if (scrollUp)
               translate(0, scrollSpeed);
            if (scrollDown)
               translate(0, -scrollSpeed);
            if (scrollRight)
               translate(-scrollSpeed, 0);
            if (scrollLeft)
               translate(scrollSpeed, 0);
         }
      
      }
   
      public void resetBackUpEntityArrays()
      {
         backupEntities = new ArrayList<Entity>();
         backupfrontEntities = new ArrayList<Entity>();
         backupbackEntities = new ArrayList<Entity>();
      }
   
      public void resetEntityArrays()
      {
         entities = new ArrayList<Entity>();
         frontEntities = new ArrayList<Entity>();
         backEntities = new ArrayList<Entity>();
      }
   
      public void backUpEntities()
      {
         resetBackUpEntityArrays();
      
         backupEntities = entities;
         backupfrontEntities = frontEntities;
         backupbackEntities = backEntities;
      }
   
      public void reloadEntities()
      {
         resetEntityArrays();
      
         entities = backupEntities;
         frontEntities = backupfrontEntities;
         backEntities = backupbackEntities;
      }
   
      public void resumeAll()
      {
         for (int i = 0; i < entities.size(); i++)
         {
            entities.get(i).unpause();
         }
         physics = true;
      }
   
      public void pauseAll()
      {
         for (int i = 0; i < entities.size(); i++)
         {
            entities.get(i).pause();
         }
         physics = false;
      }
   
      public void goInGame() // player stuff
      {
         inGame = true;
         resumeAll();
         dude = dude.convert(spawnRect.getX(), spawnRect.getY(), 0, 0, true, entities, 100);
      }
   
      public void goInEditor()
      {
         pauseAll();
         inGame = false;
      		// opposite of the above method, takes the player out and makes
      		// it an
      		// editor again
      }
   
      public void save() // editing tools
      {
         String s = ""; // this is gonna be one fat ass mutha string
         s += "SpawnRect " + spawnRect.getX() + " " + spawnRect.getY();
         s += "\nbackEntities\n";
         for (int i = 0; i < backEntities.size(); i++)
         {
            s += backEntities.get(i).getSaveString();
            s += "\n";
         }
         s += "entities\n";
         for (int i = 0; i < entities.size(); i++)
         {
            s += entities.get(i).getSaveString();
            s += "\n";
         }
         s += "frontEntities\n";
         for (int i = 0; i < frontEntities.size(); i++)
         {
            s += frontEntities.get(i).getSaveString();
            s += "\n";
         }
      		// have a current file variable so you can save to multiple
      		// places
      		// back up system - saves what you do under the same name but in
      		// a
      		// deeper folder for safety
         s += "end";
         Saver savez = new Saver(fileName);
         savez.update(s);
      }
   
      public void load()
      {
         System.out.println("honk");
         String addArray = "entities";
         Scanner scan = null;
         resetEntityArrays();
         resetBackUpEntityArrays();
         try
         {
            scan = new Scanner(new File(fileName + ".txt"));
         
            while (scan.hasNext())
            {
               String s = scan.next(); // will equal the type
            										// name
            
               if (s.equals("StaticEntity"))
               {
                  String n = scan.next();
                  int id = (int) Double.parseDouble(scan.next());
                  double inx = Double.parseDouble(scan.next());
                  double iny = Double.parseDouble(scan.next());
                  double fric = Double.parseDouble(scan.next());
                  double h = Double.parseDouble(scan.next());
                  String is = scan.next();
                  Image i = getImage(getDocumentBase(), is);
               
                  if (addArray.equals("frontEntities"))
                     frontEntities.add(new StaticEntity(n, id, inx, iny, fric, h, i, is));
                  else if (addArray.equals("entities"))
                     entities.add(new StaticEntity(n, id, inx, iny, fric, h, i, is));
                  else if (addArray.equals("backEntities"))
                     backEntities.add(new StaticEntity(n, id, inx, iny, fric, h, i, is));
               }
               else if (s.equals("PhysicsEntity"))
               {
                  String n = scan.next();
                  int id = (int) Double.parseDouble(scan.next());
                  double inx = Double.parseDouble(scan.next());
                  double iny = Double.parseDouble(scan.next());
                  double fps = Double.parseDouble(scan.next());
                  String ss = scan.next();
                  Image i = getImage(getDocumentBase(), ss);
                  double mass = Double.parseDouble(scan.next());
                  double fric = Double.parseDouble(scan.next());
                  double health = Double.parseDouble(scan.next());
               
                  if (addArray.equals("frontEntities"))
                     frontEntities.add(new PhysicsEntity(n, id, inx, iny, fps, i, ss, mass, fric, health));
                  else if (addArray.equals("entities"))
                     entities.add(new PhysicsEntity(n, id, inx, iny, fps, i, ss, mass, fric, health));
                  else if (addArray.equals("backEntities"))
                     backEntities.add(new PhysicsEntity(n, id, inx, iny, fps, i, ss, mass, fric, health));
               }
               else if (s.equals("Particle"))
               {
                  String name = scan.next();
                  int id = (int) Double.parseDouble(scan.next());
                  double inx = Double.parseDouble(scan.next());
                  double iny = Double.parseDouble(scan.next());
                  double grav = Double.parseDouble(scan.next());
                  double fps = Double.parseDouble(scan.next());
                  String ss = scan.next();
                  Image i = getImage(getDocumentBase(), ss);
                  double mass = Double.parseDouble(scan.next());
                  double fric = Double.parseDouble(scan.next());
                  double time = Double.parseDouble(scan.next());
               
                  if (addArray.equals("frontEntities"))
                     frontEntities.add(new Particle(name, id, inx, iny, grav, fps, i, ss, mass, fric, time));
                  else if (addArray.equals("entities"))
                     entities.add(new Particle(name, id, inx, iny, grav, fps, i, ss, mass, fric, time));
                  else if (addArray.equals("backEntities"))
                     backEntities.add(new Particle(name, id, inx, iny, grav, fps, i, ss, mass, fric, time));
               }
               else if (s.equals("ParticleSpawner"))
               {
                  String name = scan.next();
                  int id = (int) Double.parseDouble(scan.next());
                  double inx = Double.parseDouble(scan.next());
                  double iny = Double.parseDouble(scan.next());
                  double addTime = Double.parseDouble(scan.next());
               					// particle
                  scan.next(); // to get rid of the
               									// "Particle"
                  String partname = scan.next();
                  int partid = (int) Double.parseDouble(scan.next());
                  double partinx = Double.parseDouble(scan.next());
                  double partiny = Double.parseDouble(scan.next());
                  double partgrav = Double.parseDouble(scan.next());
                  double partfps = Double.parseDouble(scan.next());
                  String partss = scan.next();
                  Image parti = getImage(getDocumentBase(), partss);
                  double partmass = Double.parseDouble(scan.next());
                  double partfric = Double.parseDouble(scan.next());
                  double parttime = Double.parseDouble(scan.next());
                  Particle p = new Particle(partname, partid, partinx, partiny, partgrav, partfps, parti, partss, partmass, partfric, parttime);
               					// end particle
                  double maxx = Double.parseDouble(scan.next());
                  double minx = Double.parseDouble(scan.next());
                  double maxy = Double.parseDouble(scan.next());
                  double miny = Double.parseDouble(scan.next());
                  double speed = Double.parseDouble(scan.next());
               
                  if (addArray.equals("frontEntities"))
                     frontEntities.add(new ParticleSpawner(name, id, inx, iny, addTime, p, maxx, minx, maxy, miny, speed));
                  else if (addArray.equals("entities"))
                     entities.add(new ParticleSpawner(name, id, inx, iny, addTime, p, maxx, minx, maxy, miny, speed));
                  else if (addArray.equals("backEntities"))
                     backEntities.add(new ParticleSpawner(name, id, inx, iny, addTime, p, maxx, minx, maxy, miny, speed));
               
               }
               else if (s.equals("Bullet"))
               {
               
                  double inx = Double.parseDouble(scan.next());
                  double iny = Double.parseDouble(scan.next());
                  double xs = Double.parseDouble(scan.next());
                  double ys = Double.parseDouble(scan.next());
                  double gr = Double.parseDouble(scan.next());
                  double mss = Double.parseDouble(scan.next());
                  double dmg = Double.parseDouble(scan.next());
                  int hits = (int) Double.parseDouble(scan.next());
                  double maxT = Double.parseDouble(scan.next());
                  double frames = Double.parseDouble(scan.next());
               
                  if (addArray.equals("frontEntities"))
                     frontEntities.add(new Bullet(inx, iny, xs, ys, gr, mss, dmg, hits, maxT, frames));
                  else if (addArray.equals("entities"))
                     entities.add(new Bullet(inx, iny, xs, ys, gr, mss, dmg, hits, maxT, frames));
                  else if (addArray.equals("backEntities"))
                     entities.add(new Bullet(inx, iny, xs, ys, gr, mss, dmg, hits, maxT, frames));
               
               }
               else if (s.equals("EnemySpawn"))
               {
                  String namez = scan.next();
                  double inx = Double.parseDouble(scan.next());
                  double iny = Double.parseDouble(scan.next());
                  String is = scan.next();
                  if (addArray.equals("frontEntities"))
                     frontEntities.add(new EnemySpawn(namez, inx, iny, getImage(url, is), is, (Enemy) enemies.get(0)));
                  else if (addArray.equals("entities"))
                     entities.add(new EnemySpawn(namez, inx, iny, getImage(url, is), is, (Enemy) enemies.get(0)));
                  else if (addArray.equals("backEntities"))
                     entities.add(new EnemySpawn(namez, inx, iny, getImage(url, is), is, (Enemy) enemies.get(0)));
               
               }
               else if (s.equals("backEntities"))
               {
                  addArray = "backEntities";
               }
               else if (s.equals("entities"))
               {
                  addArray = "entities";
               }
               else if (s.equals("frontEntities"))
               {
                  addArray = "frontEntities";
               }
               else if (s.equals("SpawnRect"))
               {
                  s = scan.next();
                  double tempX = Double.parseDouble(s);
                  s = scan.next();
                  double tempY = Double.parseDouble(s);
                  spawnRect.setLocation((int) tempX, (int) tempY);
               }
            				// else if(s.equals("end"))
            
            }
         }
            catch (FileNotFoundException e)
            {}
      }
   
      public void convertToData()
      {
         load();
         String tempLine = "";
         int tempStart = 0, tempEnd = 0;
         ArrayList<Entity> currentLayer = backEntities;
         Entity currentEntity;
      
         for (int i = 0; i < lineCount; i++) // starts on line three with
         										// "backEntities", iterator
         										// goes line by line
         {
            tempEnd = saveInfo.indexOf("\n", tempStart); // finds
         															// "\n"
         															// starting
         															// from
         															// tempStart
            tempLine = saveInfo.substring(tempStart, tempEnd);
         
            if (tempLine.equals("entities"))
               currentLayer = entities;
            if (tempLine.equals("frontEntities"))
               currentLayer = frontEntities;
         
            if (tempLine.substring(0, 12).equals("StaticEntity"))
            {
               int paramLoc, paramEnd;
               paramLoc = 39;
               paramEnd = tempLine.indexOf(" ", paramLoc);
               int inID = Integer.parseInt(tempLine.substring(paramLoc, paramEnd)); // test
            																						// this
            																						// usage
               paramLoc = paramEnd;
               paramEnd = tempLine.indexOf(" ", paramLoc + 1);
               double inx = Double.parseDouble(tempLine.substring(paramLoc, paramEnd));
               paramLoc = paramEnd;
               paramEnd = tempLine.indexOf(" ", paramLoc + 1);
               double iny = Double.parseDouble(tempLine.substring(paramLoc, paramEnd));
               paramLoc = paramEnd;
               paramEnd = tempLine.indexOf(" ", paramLoc + 1);
               double fric = Double.parseDouble(tempLine.substring(paramLoc, paramEnd));
               paramLoc = paramEnd;
               paramEnd = tempLine.indexOf(" ", paramLoc + 1);
               double h = Double.parseDouble(tempLine.substring(paramLoc, paramEnd));
               String imgStr = tempLine.substring(paramEnd);
            				// Image img=createImage();
            
               currentEntity = new StaticEntity("", inID, inx, iny, fric, h, getImage(getDocumentBase(), fileName), imgStr);
               backEntities.add(currentEntity);
            }
         
            if (tempLine.substring(0, 13).equals("PhysicsEntity"))
               ;
         
            if (tempLine.substring(0, 7).equals("Particle"))
               ;
         
            tempStart = tempEnd + 2;
         }
      }
   
      public void placeEntity()
      {
         currentEntity.pause();
         currentEntity.setID(idCount);
         currentEntity.convert(mx, my, idCount);
         Entity e = currentEntity;
         if (e.getType().equals("StaticEntity"))
            entities.add(new StaticEntity(e.getName(), idCount, mx, my, e.getFriction(), e.getHealth(), e.getImage(), e.getImageString()));
         else if (e.getType().equals("PhysicsEntity"))
            entities.add(new PhysicsEntity(e.getName(), idCount, mx, my, fps, e.getImage(), e.getImageString(), e.getMass(), e.getFriction(), e.getHealth()));
         else if (e.getType().equals("Particle"))
            entities.add(new Particle(e.getName(), idCount, mx, my, fps, e.getGravity(), e.getImage(), e.getImageString(), e.getMass(), e.getFriction(), e.getMaxTime()));
         else if (e.getType().equals("ParticleSpawner"))
         {
            Entity e2 = new ParticleSpawner("", 0, null, 0, 0, 0, 0, 0);
            e2.sync(e);
            entities.add(e2);
         }
         else if (e.getType().equals("EnemySpawn"))
         {
            backEntities.add(new EnemySpawn(e.getName(), mx, my, e.getImage(), e.getImageString(), ((EnemySpawn) e).getEnemy()));
         }
         idCount++;
      }
   
      public void nextStaticProp()
      {
         if (staticProps.size() != 0)
         {
            if (staticPropPos == staticProps.size() - 1)
               staticPropPos = 0;
            else
               staticPropPos++;
         
            currentEntity = staticProps.get(staticPropPos);
         }
      }
   
      public void lastStaticProp()
      {
         if (staticProps.size() != 0)
         {
            if (staticPropPos == 0)
               staticPropPos = staticProps.size() - 1;
            else
               staticPropPos--;
            currentEntity = staticProps.get(staticPropPos);
         }
      }
   
      public void nextPhysProp()
      {
         if (physProps.size() != 0)
         {
            if (physPropPos == physProps.size() - 1)
               physPropPos = 0;
            else
               physPropPos++;
         
            currentEntity = physProps.get(physPropPos);
         }
      }
   
      public void lastPhysProp()
      {
         if (physProps.size() != 0)
         {
            if (physPropPos == 0)
               physPropPos = physProps.size() - 1;
            else
               physPropPos--;
            currentEntity = physProps.get(physPropPos);
         }
      }
   
      public void nextMiscStuff()
      {
         if (miscStuff.size() != 0)
         {
            if (miscStuffPos == miscStuff.size() - 1)
               miscStuffPos = 0;
            else
               miscStuffPos++;
         
            currentEntity = miscStuff.get(miscStuffPos);
         }
      }
   
      public void lastMiscStuff()
      {
         if (miscStuff.size() != 0)
         {
            if (miscStuffPos == 0)
               miscStuffPos = miscStuff.size() - 1;
            else
               miscStuffPos--;
            currentEntity = miscStuff.get(miscStuffPos);
         }
      }
   
      public void nextEnemy()
      {
         if (enemies.size() != 0)
         {
            if (enemiesPos == enemies.size() - 1)
               enemiesPos = 0;
            else
               enemiesPos++;
         
            currentEntity = enemies.get(enemiesPos);
         }
      }
   
      public void lastEnemy()
      {
         if (enemies.size() != 0)
            ;
         {
            if (enemiesPos == 0)
               enemiesPos = enemies.size() - 1;
            else
               enemiesPos--;
            currentEntity = enemies.get(enemiesPos);
         }
      }
   
      public void sendToBack()
      {
         Entity it = null;
         int where = -1;
         String s = "";
         for (int i = 0; i < backEntities.size(); i++)
         {
            if (backEntities.get(i).getEditorRectangle().contains(mx, my))
            {
               it = backEntities.get(i);
               where = i;
               s = "b";
            }
         }
         for (int i = 0; i < frontEntities.size(); i++)
         {
            if (frontEntities.get(i).getEditorRectangle().contains(mx, my))
            {
               it = frontEntities.get(i);
               where = i;
               s = "f";
            }
         }
         for (int i = 0; i < entities.size(); i++)
         {
            if (entities.get(i).getEditorRectangle().contains(mx, my))
            {
               it = entities.get(i);
               where = i;
               s = "p";
            }
         }
         if (it != null)
         {
            if (s.equals("p"))
            {
               entities.add(0, it);
               entities.remove(where + 1);
            }
            else if (s.equals("b"))
            {
               backEntities.add(0, it);
               backEntities.remove(where + 1);
            }
            else if (s.equals("f"))
            {
               frontEntities.add(0, it);
               frontEntities.remove(where + 1);
            }
         }
      }
   
      public void sendToFront()
      {
         Entity it = null;
         int where = -1;
         String s = "";
         for (int i = 0; i < backEntities.size(); i++)
         {
            if (backEntities.get(i).getEditorRectangle().contains(mx, my))
            {
               it = backEntities.get(i);
               where = i;
               s = "b";
            }
         }
         for (int i = 0; i < frontEntities.size(); i++)
         {
            if (frontEntities.get(i).getEditorRectangle().contains(mx, my))
            {
               it = frontEntities.get(i);
               where = i;
               s = "f";
            }
         }
         for (int i = 0; i < entities.size(); i++)
         {
            if (entities.get(i).getEditorRectangle().contains(mx, my))
            {
               it = entities.get(i);
               where = i;
               s = "p";
            }
         }
         if (it != null)
         {
            if (s.equals("p"))
            {
               entities.add(it);
               entities.remove(where + 1);
            }
            else if (s.equals("b"))
            {
               backEntities.add(it);
               backEntities.remove(where + 1);
            }
            else if (s.equals("f"))
            {
               frontEntities.add(it);
               frontEntities.remove(where + 1);
            }
         }
      }
   
      public void sendToFrontLayer()
      {
         Entity it = null;
         int where = -1;
         String s = "";
         for (int i = 0; i < backEntities.size(); i++)
         {
            if (backEntities.get(i).getEditorRectangle().contains(mx, my))
            {
               it = backEntities.get(i);
               where = i;
               s = "Back Layer";
            }
         }
         for (int i = 0; i < entities.size(); i++)
         {
            if (entities.get(i).getEditorRectangle().contains(mx, my))
            {
               it = entities.get(i);
               where = i;
               s = "Play Layer";
            }
         }
         if (it != null)
         {
            frontEntities.add(it);
            if (s.equals("Play Layer"))
               entities.remove(where);
            else if (s.equals("Back Layer"))
               backEntities.remove(where);
         }
      }
   
      public void sendToPlayLayer()
      {
         Entity it = null;
         int where = -1;
         String s = "";
         for (int i = 0; i < backEntities.size(); i++)
         {
            if (backEntities.get(i).getEditorRectangle().contains(mx, my))
            {
               it = backEntities.get(i);
               where = i;
               s = "Back Layer";
            }
         }
         for (int i = 0; i < frontEntities.size(); i++)
         {
            if (frontEntities.get(i).getEditorRectangle().contains(mx, my))
            {
               it = frontEntities.get(i);
               where = i;
               s = "Front Layer";
            }
         }
      
         if (it != null)
         {
            entities.add(it);
            if (s.equals("Front Layer"))
               frontEntities.remove(where);
            else if (s.equals("Back Layer"))
               backEntities.remove(where);
         }
      }
   
      public void sendToBackLayer()
      {
         Entity it = null;
         int where = -1;
         String s = "";
         for (int i = 0; i < entities.size(); i++)
         {
            if (entities.get(i).getEditorRectangle().contains(mx, my))
            {
               it = entities.get(i);
               where = i;
               s = "Play Layer";
            }
         }
         for (int i = 0; i < frontEntities.size(); i++)
         {
            if (frontEntities.get(i).getEditorRectangle().contains(mx, my))
            {
               it = frontEntities.get(i);
               where = i;
               s = "Front Layer";
            }
         }
      
         if (it != null)
         {
            backEntities.add(it);
            if (s.equals("Play Layer"))
               entities.remove(where);
            else if (s.equals("Front Layer"))
               frontEntities.remove(where);
         }
      }
   
      public void removeEntityOnMouse()
      {
         int remove = -1;
         String s = "";
         for (int i = 0; i < backEntities.size(); i++)
         {
            if (backEntities.get(i).getEditorRectangle().contains(mx, my))
            {
               remove = i;
               s = "b";
            }
         }
         for (int i = 0; i < entities.size(); i++)
         {
            if (entities.get(i).getEditorRectangle().contains(mx, my))
            {
               remove = i;
               s = "p";
            }
         }
         for (int i = 0; i < frontEntities.size(); i++)
         {
            if (frontEntities.get(i).getEditorRectangle().contains(mx, my))
            {
               remove = i;
               s = "f";
            }
         }
      
         if (remove != -1)
         {
            if (s.equals("p"))
               entities.remove(remove);
            else if (s.equals("f"))
               frontEntities.remove(remove);
            else if (s.equals("b"))
               backEntities.remove(remove);
         }
      }
   
      public void displayExtraInfo(Graphics2D g, Entity e, String s, String t)
      {
         Color previousColor = g.getColor();
         g.setColor(Color.cyan);
      
         String info0 = e.getID() + " " + e.getName();
         String info1 = "X: " + e.getX();
         String info2 = "Y: " + e.getY();
         String info3 = "Mass: " + e.getMass();
         String info5 = "Friction: " + e.getFriction();
         String info6 = "Array: " + s;
         String info7 = "Type of Object: " + t;
         String info8 = "Health: " + e.getHealth();
         g.drawString(info0, (int) e.getX() + 2, (int) e.getY() + 15);
         g.drawString(info1, (int) e.getX() + 2, (int) e.getY() + 30);
         g.drawString(info2, (int) e.getX() + 2, (int) e.getY() + 45);
         g.drawString(info3, (int) e.getX() + 2, (int) e.getY() + 60);
         g.drawString(info5, (int) e.getX() + 2, (int) e.getY() + 75);
         g.drawString(info6, (int) e.getX() + 2, (int) e.getY() + 90);
         g.drawString(info7, (int) e.getX() + 2, (int) e.getY() + 105);
         g.drawString(info8, (int) e.getX() + 2, (int) e.getY() + 120);
      
         g.setColor(previousColor);
      }
   
      public void displayInfoOnMouseOver(Graphics2D g)
      {
         Entity it = null;
         String s = "";
         String t = "";
         for (Entity e : backEntities)
         {
            if (e.getEditorRectangle().contains(mx, my))
            {
               it = e;
               s = "Back Layer";
            }
         }
         for (Entity e : entities)
         {
            if (e.getEditorRectangle().contains(mx, my))
            {
               it = e;
               s = "Play Layer";
            }
         }
         for (Entity e : frontEntities)
         {
            if (e.getEditorRectangle().contains(mx, my))
            {
               it = e;
               s = "Front Layer";
            }
         }
         if (it == null)
            return;
         if (it instanceof Particle)
            t = "Particle";
         else if (it instanceof PhysicsEntity)
            t = "PhysicsEntity";
         else if (it instanceof StaticEntity)
            t = "StaticEntity";
         else if (it instanceof Enemy)
            t = "Enemy";
         else if (it instanceof ParticleSpawner)
            t = "ParticleSpawner";
         else
            t = it.getType();
      
         displayExtraInfo(g, it, s, t);
      }
   
   	// keyboard stuff
   	// below////////////////////////////////////////////////////////////////
      public void keyTyped(KeyEvent e)
      {
         if (!inGame)
         {
            switch (e.getKeyCode())
            {
            
            }
         }
         if (inGame)
         {
            switch (e.getKeyCode())
            {
               case KeyEvent.VK_SPACE:
               
                  break;
            }
         }
      }
   
      public void keyPressed(KeyEvent e) // means key hit (and held if you
      // reverse
      // it in keyReleased)
      {
         if (!inGame)
         {
            switch (e.getKeyCode())
            {
            
               case KeyEvent.VK_DOWN: // entity movement
                  if (ctrl && !shift)
                     my += 1;
                  else if (shift && !ctrl)
                     my += currentEntity.getYDim();
                  else if (shift && ctrl)
                     my += currentEntity.getYDim() / 4;
                  else
                     my += 10;
                  break;
               case KeyEvent.VK_UP: // entity movement
                  if (ctrl && !shift)
                     my -= 1;
                  else if (shift && !ctrl)
                     my -= currentEntity.getYDim();
                  else if (shift && ctrl)
                     my -= currentEntity.getYDim() / 4;
                  else
                     my -= 10;
                  break;
               case KeyEvent.VK_LEFT: // entity movement
                  if (ctrl && !shift)
                     mx -= 1;
                  else if (shift && !ctrl)
                     mx -= currentEntity.getXDim();
                  else if (shift && ctrl)
                     mx -= currentEntity.getXDim() / 4;
                  else
                     mx -= 10;
                  break;
               case KeyEvent.VK_RIGHT: // entity movement
                  if (ctrl && !shift)
                     mx += 1;
                  else if (shift && !ctrl)
                     mx += currentEntity.getXDim();
                  else if (shift && ctrl)
                     mx += currentEntity.getXDim() / 4;
                  else
                     mx += 10;
                  break;
               case KeyEvent.VK_D: // screen movement
                  scrollRight = true;
                  break;
               case KeyEvent.VK_A: // screen movement
                  scrollLeft = true;
                  break;
               case KeyEvent.VK_W: // screen movement
                  scrollUp = true;
                  break;
               case KeyEvent.VK_S: // screen movement
                  if (!ctrl && !shift)
                     scrollDown = true;
                  else if (ctrl && !shift)
                     save();
                  else if (shift && !ctrl)
                  {
                     spawnRectX = mx;
                     spawnRectY = my;
                     spawnRect.setLocation((int) spawnRectX, (int) spawnRectY);
                  }
                  break;
               case KeyEvent.VK_U:
                  nextEnemy();
                  break;
               case KeyEvent.VK_J:
                  lastEnemy();
                  break;
               case KeyEvent.VK_I: // placing blocks
                  nextStaticProp();
                  break;
               case KeyEvent.VK_K:
                  lastStaticProp();
                  break;
               case KeyEvent.VK_O: // placing blocks
                  nextPhysProp();
                  break;
               case KeyEvent.VK_L:
                  if (!shift && !ctrl)
                     load();
                  break;
               case KeyEvent.VK_P: // placing blocks
                  nextMiscStuff();
                  break;
               case KeyEvent.VK_SEMICOLON:
                  lastMiscStuff();
                  break;
               case KeyEvent.VK_B:
                  if (shift && !ctrl)
                     sendToPlayLayer();
                  break;
               case KeyEvent.VK_H: // physics on/off
                  if (physics && ctrl)
                  {
                     pauseAll();
                     physics = false;
                     reloadEntities();
                  }
                  else if (ctrl && !physics)
                  {
                     backUpEntities();
                     resumeAll();
                     physics = true;
                     backUpEntities();
                  }
                  if (shift && !ctrl)
                  {
                     if (drawHelp)
                        drawHelp = false;
                     else if (!drawHelp)
                        drawHelp = true;
                  }
                  break;
               case KeyEvent.VK_R:
                  if (ctrl && !shift)
                     reloadEntities();
                  break;
               case KeyEvent.VK_Z:
               
                  break;
               case KeyEvent.VK_G: // go in/out of game
                  if (ctrl && !shift)
                  {
                     goInGame();
                  }
                  break;
               case KeyEvent.VK_N: // not racist here.... nope
                  if (!shift && !ctrl)
                     sendToBack();
                  else if (shift && !ctrl)
                     sendToBackLayer();
                  break;
               case KeyEvent.VK_M:
                  if (!shift && !ctrl)
                     sendToFront();
                  else if (shift && !ctrl)
                     sendToFrontLayer();
                  break;
               case KeyEvent.VK_SPACE: // space bar
                  placeEntity();
                  break;
            }
         }
         else if (inGame) // /////////////////////////////////in game
         	// controls
         {
            switch (e.getKeyCode())
            {
               case KeyEvent.VK_A:
                  if (!shift && !ctrl)
                     playerMovingL = true;
                  else if (shift && !ctrl)
                     playerSprintingL = true;
                  break;
               case KeyEvent.VK_D:
                  if (!shift && !ctrl)
                     playerMovingR = true;
                  else if (shift && !ctrl)
                     playerSprintingR = true;
                  break;
               case KeyEvent.VK_SPACE:
                  jump = true;
                  break;
               case KeyEvent.VK_G:
                  if (ctrl && !shift)
                     goInEditor();
                  break;
            }
         
         } // modifiers (all use)
         switch (e.getKeyCode())
         {
         
            case KeyEvent.VK_CONTROL:
               ctrl = true;
               break;
            case KeyEvent.VK_SHIFT:
               shift = true;
               break;
         }
      
      }
   
      public void keyReleased(KeyEvent e)
      {
         if (!inGame)
         {
            switch (e.getKeyCode())
            {
               case KeyEvent.VK_A:
                  scrollLeft = false;
                  break;
               case KeyEvent.VK_D:
                  scrollRight = false;
                  break;
               case KeyEvent.VK_S:
                  scrollDown = false;
                  break;
               case KeyEvent.VK_W:
                  scrollUp = false;
                  break;
               case KeyEvent.VK_DOWN:
               
                  break;
               case KeyEvent.VK_UP:
               
                  break;
               case KeyEvent.VK_LEFT:
               
                  break;
               case KeyEvent.VK_RIGHT:
               
                  break;
               case KeyEvent.VK_SPACE:
               
                  break;
               case KeyEvent.VK_Z:
               
                  break;
            
            }
         }
         else if (inGame) // /////////////////////in game key releases
         {
            switch (e.getKeyCode())
            {
               case KeyEvent.VK_A:
                  playerMovingL = false;
                  playerSprintingL = false;
                  break;
               case KeyEvent.VK_D:
                  playerMovingR = false;
                  playerSprintingR = false;
                  break;
               case KeyEvent.VK_SPACE:
                  jump = false;
                  break;
            
            }
         }
         switch (e.getKeyCode())
         {
            case KeyEvent.VK_CONTROL:
               ctrl = false;
               break;
            case KeyEvent.VK_SHIFT:
               shift = false;
               playerSprintingR = false;
               playerSprintingL = false;
               break;
         
         }
      }
   
   	// mouse stuff
   	// below////////////////////////////////////////////////////////////////
      public void mouseEntered(MouseEvent e)
      {
         mx = e.getX();
         my = e.getY();
      		// called when the pointer enters the applet's rectangular area
      }
   
      public void mouseExited(MouseEvent e)
      {
         mx = e.getX();
         my = e.getY();
      		// called when the pointer leaves the applet's rectangular area
      }
   
      public void mouseClicked(MouseEvent e)
      {
         if (!inGame)
         {
            switch (e.getModifiers())
            {
               case InputEvent.BUTTON1_MASK: // left
                  placeEntity();
                  break;
               case InputEvent.BUTTON2_MASK: // middle
               
                  break;
               case InputEvent.BUTTON3_MASK: // right
                  removeEntityOnMouse();
                  break;
            }
         
         }
      		// called after a press and release of a mouse button
      		// with no motion in between
      		// (If the user presses, drags, and then releases, there will be
      		// no click event generated.)
      }
   
      public void mousePressed(MouseEvent e)
      {// called after a button is pressed
      		// down
      		// "Consume" the event so it won't
      		// be processed in the
      		// default manner by the source
      		// which generated it.
         mx = e.getX();
         my = e.getY();
      
         if (inGame)
            switch (e.getModifiers())
            {
               case InputEvent.BUTTON1_MASK: // left
                  {
                     double bulX = dude.getX() + 150;
                     double bulY = dude.getY() + 34;
                     if (mx < dude.getX() + 50)
                        bulX = dude.getX() - 42;
                     double bx = mx - bulX;
                     double by = my - bulY;
                     double angle = Math.atan(by / (bx + .0000001)); // the .000001 is to prevent dividing by 0
                     double bulSpeed = 2000;
                  
                     double xSpd = bulSpeed * Math.cos(angle);
                     double ySpd = bulSpeed * Math.sin(angle);
                     if (mx < bulX)
                     {
                        xSpd = -bulSpeed * Math.cos(angle);
                        ySpd = -bulSpeed * Math.sin(angle);
                     }
                     double grv = 0;
                     double mss = .05;
                     double dm = 5;
                     int hitz = 3;
                     double timez = 2;
                     Bullet b = new Bullet(bulX, bulY, xSpd, ySpd, grv, mss, dm, hitz, timez, fps);
                     entities.add(b);
                     dude.updateEntities(performanceEntities);
                  			// dude.shoot(mx, my);
                  }
                  break;
               case InputEvent.BUTTON2_MASK: // middle
                  {
                  
                     break;
                  }
               case InputEvent.BUTTON3_MASK: // right
                  {
                  
                  }
                  break;
            }
      
      }
   
      public void mouseReleased(MouseEvent e)
      {// called after a button is
      		// released
         mx = e.getX();
         my = e.getY();
      
      }
   
      public void mouseMoved(MouseEvent e)
      {// called when the mouse changes
      		// position
         mx = e.getX();
         my = e.getY();
         currentEntity.renderInEditor(gfx);
         gfx.drawImage(currentEntity.getImage(), mx, my, this);
      		// e.consume();
      }
   
      public void mouseDragged(MouseEvent e)
      {// called when the mouse is clicked
      		// then moved
      		// only updates after click is let
      		// go
      		//mousePressed(e);
         mx = e.getX();
         my = e.getY();
      
      }
   
   }

/*
 * NOTES NOTES NOTES NOTES NOTES NOTES NOTES NOTES NOTES NOTES NOTES NOTES NOTES
 * 
 * http://www.dreamincode.net/forums/topic/14083-incredibly-easy-way-to-play-sounds
 * /
 * 
 * 
 * Map Save File Format Name "     "
 * 
 * Pieces (dont use commas, just .getNextWord or something like that) pretty
 * much just have the entity parameters here
 */
