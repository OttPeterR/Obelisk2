import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.security.CodeSource;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Saver extends JFrame implements ActionListener, PropertyChangeListener
{
	private static final long	serialVersionUID	= 7506091502820512509L;

	int							resx				= 450;
	int							resy				= 100;

	ArrayList<Entity>			frontEnts, playEnts, backEnts;
	ArrayList<AINode>			nodes;
	Vect						spawn;

	String						fileName			= "";
	JFormattedTextField			fileNameField		= new JFormattedTextField(fileName);
	ArrayList<String>			names				= new ArrayList<String>();

	public Saver(ArrayList<Entity> fE, ArrayList<Entity> pE, ArrayList<Entity> bE, ArrayList<AINode> aiNodes, Vect sp)
	{
		frontEnts = fE;
		playEnts = pE;
		backEnts = bE;
		nodes = aiNodes;
		spawn = sp;
		this.setSize(resx, resy);
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - resx) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - resy) / 2);
		this.setIconImage(Library.img("PROTekIconSmall.png"));

		setTitle("Save As");
		init();
	}

	public void init()
	{
		JButton saveButton = new JButton("Save");
		saveButton.setActionCommand("save");
		saveButton.addActionListener(this);
		// /////////////////////////////////////////
		JPanel labels = new JPanel(new GridLayout(0, 2));
		labels.setBorder(new EmptyBorder(10, 10, 10, 10));
		labels.add(new JLabel("File Name"));
		labels.add(fileNameField);
		labels.add(saveButton);

		add(labels);

	}

	private void save()
	{
		if (!fileNameField.getText().equals(""))
			try
			{
				fileName = fileNameField.getText();

				CodeSource cs = Saver.class.getProtectionDomain().getCodeSource();
				String loc = cs.getLocation().toString() + "Saves/";
				loc = loc.substring(6);
				FileWriter save = new FileWriter(loc + fileName + ".txt");
				BufferedWriter out = new BufferedWriter(save);

				String NL = System.getProperty("line.separator");

				out.write(fileName + "" + NL);
				out.write(spawn + NL);
				out.write("backEnts" + NL);
				for (int i = 0; i < backEnts.size(); i++)
				{
					out.write(backEnts.get(i).saveString());
					out.write(NL);

				}
				out.write("playEnts" + NL);
				for (int i = 0; i < playEnts.size(); i++)
				{
					out.write(playEnts.get(i).saveString());
					out.write(NL);
				}
				out.write("frontEnts" + NL);
				for (int i = 0; i < frontEnts.size(); i++)
				{
					out.write(frontEnts.get(i).saveString());
					out.write(NL);
				}
				out.write("AINodes" + NL);
				for (int i = 0; i < nodes.size(); i++)
				{
					out.write(nodes.get(i).saveString());
					out.write(NL);
				}

				out.close();

			}
			catch (Exception e)
			{
				System.out.println("Saver.java Error: " + e.toString());
			}
	}
	public void propertyChange(PropertyChangeEvent arg0)
	{}
	public void actionPerformed(ActionEvent ev)
	{
		String com = ev.getActionCommand();

		if (com.equals("save"))
		{
			save();
			setVisible(false);
		}
	}
}
