import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class EntityEditor extends JFrame implements ActionListener, PropertyChangeListener
{
	private static final long	serialVersionUID	= 7506091502820512509L;

	ArrayList<Entity>			frontEnts, backEnts, playEnts;

	int							resx				= 350;
	int							resy				= 600;
	Entity						e, eBackUp;
	JLabel						name				= new JLabel("Name");
	JFormattedTextField			nF;
	JLabel						xCoord				= new JLabel("X Position");
	JFormattedTextField			xF;
	JLabel						yCoord				= new JLabel("Y Position");
	JFormattedTextField			yF;
	JLabel						ang					= new JLabel("Angle");
	JFormattedTextField			aF;
	JLabel						xSpd				= new JLabel("X Speed");
	JFormattedTextField			ysF;
	JLabel						ySpd				= new JLabel("Y Speed");
	JFormattedTextField			xsF;
	JLabel						angAccel			= new JLabel("Angular Acceleration");
	JFormattedTextField			aaF;
	JLabel						grav				= new JLabel("Gravity");
	JFormattedTextField			gF;
	JLabel						zeroG				= new JLabel("Zero Gravity");
	JLabel						health				= new JLabel("Health");
	JFormattedTextField			hF;
	JLabel						fullHealth			= new JLabel("Full Health");
	JFormattedTextField			fhF;
	JLabel						invul				= new JLabel("Invulnerable");
	JLabel						damageThresh		= new JLabel("Damage Threshhold");
	JFormattedTextField			dtF;
	JLabel						timeRem				= new JLabel("Time Remaining");
	JFormattedTextField			trF;
	JLabel						fullTimeRem			= new JLabel("Full Time Remaining");
	JFormattedTextField			ftrF;
	JLabel						mass				= new JLabel("Mass");
	JFormattedTextField			mF;
	JLabel						fric				= new JLabel("Friction");
	JFormattedTextField			fF;
	JLabel						bounce				= new JLabel("Bounce");
	JFormattedTextField			bF;
	JLabel						pen					= new JLabel("Penetration");
	JFormattedTextField			pF;

	JRadioButton				frontE				= new JRadioButton("frontEnts");
	JRadioButton				playE				= new JRadioButton("playEnts");
	JRadioButton				backE				= new JRadioButton("backEnts");

	JRadioButton				ghost				= new JRadioButton("Ghost");

	public EntityEditor(Entity ent, ArrayList<Entity> fE, ArrayList<Entity> pE, ArrayList<Entity> bE)
	{
		this.setSize(resx, resy);
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - resx) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - resy) / 2);
		playEnts = pE;
		backEnts = bE;
		frontEnts = fE;
		this.setIconImage(Library.img("PROTekIconSmall.png"));

		e = ent;
		eBackUp = ent.copy();
		setTitle(e.name + " - PROperties");
		loadPROperties();
	}
	public void loadPROperties()
	{
		nF = new JFormattedTextField(e.name);
		xF = new JFormattedTextField(e.x);
		yF = new JFormattedTextField(e.y);
		aF = new JFormattedTextField(Math.toDegrees(e.ang));
		ysF = new JFormattedTextField(e.ySpeed);
		xsF = new JFormattedTextField(e.xSpeed);
		aaF = new JFormattedTextField(e.rotSpeed);
		gF = new JFormattedTextField(e.gravity);
		hF = new JFormattedTextField(e.health);
		fhF = new JFormattedTextField(e.fullHealth);
		dtF = new JFormattedTextField(e.damageThreshold);
		trF = new JFormattedTextField(e.timeRemaining);
		ftrF = new JFormattedTextField(e.fullTimeRemaining);
		mF = new JFormattedTextField(e.mass);
		fF = new JFormattedTextField(e.friction);
		bF = new JFormattedTextField(e.bounce);
		pF = new JFormattedTextField(e.penetration);
		ghost.setSelected(e.ghost);
		// //////////////
		name.setLabelFor(nF);
		xCoord.setLabelFor(xF);
		yCoord.setLabelFor(yF);
		ang.setLabelFor(aF);
		xSpd.setLabelFor(xsF);
		ySpd.setLabelFor(ysF);
		angAccel.setLabelFor(aaF);
		grav.setLabelFor(gF);
		health.setLabelFor(hF);
		fullHealth.setLabelFor(fhF);
		damageThresh.setLabelFor(dtF);
		timeRem.setLabelFor(trF);
		fullTimeRem.setLabelFor(ftrF);
		mass.setLabelFor(mF);
		fric.setLabelFor(fF);
		bounce.setLabelFor(bF);
		pen.setLabelFor(pF);

		frontE.setActionCommand("frontEnts");
		frontE.addActionListener(this);

		playE.setActionCommand("playEnts");
		playE.addActionListener(this);

		backE.setActionCommand("backEnts");
		backE.addActionListener(this);

		JButton reset = new JButton("Reset");
		reset.setActionCommand("reset");
		reset.addActionListener(this);

		JButton apply = new JButton("Apply");
		apply.setActionCommand("apply");
		apply.addActionListener(this);

		ButtonGroup layerButtonGroup = new ButtonGroup();
		layerButtonGroup.add(frontE);
		layerButtonGroup.add(playE);
		layerButtonGroup.add(backE);

		// /////////////////////////////////////////
		JPanel params = new JPanel(new GridLayout(0, 2));
		params.setBorder(new EmptyBorder(20, 20, 20, 20));

		params.add(name);
		params.add(nF);

		params.add(xCoord);
		params.add(xF);

		params.add(yCoord);
		params.add(yF);

		params.add(ang);
		params.add(aF);

		params.add(xSpd);
		params.add(xsF);

		params.add(ySpd);
		params.add(ysF);

		params.add(angAccel);
		params.add(aaF);

		params.add(grav);
		params.add(gF);

		params.add(zeroG);
		params.add(new JLabel());

		params.add(new JLabel());
		params.add(new JLabel());

		params.add(health);
		params.add(hF);

		params.add(fullHealth);
		params.add(fhF);

		params.add(invul);
		params.add(new JLabel());

		params.add(damageThresh);
		params.add(dtF);

		params.add(timeRem);
		params.add(trF);

		params.add(fullTimeRem);
		params.add(ftrF);

		params.add(mass);
		params.add(mF);

		params.add(fric);
		params.add(fF);

		params.add(bounce);
		params.add(bF);

		params.add(pen);
		params.add(pF);

		params.add(new JLabel());
		params.add(ghost);

		params.add(new JLabel("Layer: "));
		params.add(frontE);
		params.add(new JLabel());
		params.add(playE);
		params.add(new JLabel());
		params.add(backE);

		// this.setLayout(new GridLayout(2, 2));
		params.add(reset);
		params.add(apply);

		add(params);

	}
	private void findAndRemove()
	{
		if (findInArray(playEnts))
			playEnts.remove(e);
		else if (findInArray(backEnts))
			backEnts.remove(e);
		else if (findInArray(frontEnts))
			frontEnts.remove(e);
	}
	private boolean findInArray(ArrayList<Entity> ents)
	{
		boolean r = false;
		for (int i = 0; i < ents.size(); i++)
		{
			if (ents.get(i).equals(e))
			{
				ents.remove(i);
				r = true;
				i = ents.size();
			}
		}
		return r;
	}
	public void propertyChange(PropertyChangeEvent arg0)
	{}
	public void actionPerformed(ActionEvent ev)
	{
		String com = ev.getActionCommand();
		if (com.equals("reset"))
		{
			e = null;
			e = eBackUp.copy();
			loadPROperties();
		}
		else if (com.equals("apply"))
		{
			setVisible(false);

			if (frontE.isSelected())
			{
				findAndRemove();
				frontEnts.add(e);
				e.loadArray(frontEnts);

			}
			else if (playE.isSelected())
			{
				findAndRemove();
				playEnts.add(e);
				e.loadArray(playEnts);

			}
			else if (backE.isSelected())
			{
				findAndRemove();
				backEnts.add(e);
				e.loadArray(backEnts);
			}

			e.name = nF.getText();
			e.x = Double.parseDouble(xF.getText().replaceAll(",", ""));
			e.y = Double.parseDouble(yF.getText().replaceAll(",", ""));
			e.rotate(Math.toRadians(Double.parseDouble(aF.getText())) - e.ang);
			e.xSpeed = Double.parseDouble(xsF.getText().replaceAll(",", ""));
			e.ySpeed = Double.parseDouble(ysF.getText().replaceAll(",", ""));
			e.rotSpeed = Double.parseDouble(aaF.getText().replaceAll(",", ""));
			e.gravity = Double.parseDouble(gF.getText().replaceAll(",", ""));
			e.health = Double.parseDouble(hF.getText().replaceAll(",", ""));
			e.fullHealth = Double.parseDouble(fhF.getText().replaceAll(",", ""));
			e.damageThreshold = Double.parseDouble(dtF.getText().replaceAll(",", ""));
			e.timeRemaining = Double.parseDouble(trF.getText().replaceAll(",", ""));
			e.fullTimeRemaining = Double.parseDouble(ftrF.getText().replaceAll(",", ""));
			e.mass = Double.parseDouble(mF.getText().replaceAll(",", ""));
			e.friction = Double.parseDouble(fF.getText().replaceAll(",", ""));
			e.bounce = Double.parseDouble(bF.getText().replaceAll(",", ""));
			e.penetration = Double.parseDouble(pF.getText().replaceAll(",", ""));
			e.ghost = ghost.isSelected();
			e.translate(0, 0);
			
		}
	}
}
