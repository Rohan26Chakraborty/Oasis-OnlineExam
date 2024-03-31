//importing all necessary components...
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField; 
class login extends JFrame implements ActionListener  
{  
    //Login form making....
    JButton b1,b2,b3;  
    JPanel newPanel;  
    JLabel userLabel, passLabel,message,jl,jl1,jl2;  
    JTextField  textField1, textField2,jt1,jt2;  
    login()  
    {     
        userLabel = new JLabel();  
        userLabel.setText("Username :");      
        textField1 = new JTextField(5);      
        passLabel = new JLabel(); 
        message = new JLabel(); 
        passLabel.setText("Password :");        
        textField2 = new JPasswordField(5);     
        b1 = new JButton("SUBMIT");  
        newPanel = new JPanel(new GridLayout(8, 1)); 
        newPanel.add(userLabel);     
        newPanel.add(textField1);  
        newPanel.add(passLabel);    
        newPanel.add(textField2); 
        newPanel.add(message);   
        newPanel.add(b1); 
        add(newPanel);           
        b1.addActionListener(this); 
        jl=new JLabel("Create your account");   
        newPanel.add(jl);
        b2=new JButton("Create");
        newPanel.add(b2);
        jl1=new JLabel("newUser:");
        newPanel.add(jl1);
        jt1=new JTextField();
        newPanel.add(jt1);
        jl2=new JLabel("newPassword:");
        newPanel.add(jl2);
        jt2=new JPasswordField();
        newPanel.add(jt2);
        jl1.setVisible(false);
        jt1.setVisible(false);
        jl2.setVisible(false);
        jt2.setVisible(false);
        b1.setVisible(false);
        b2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                jl1.setVisible(true);
                jt1.setVisible(true);
                jl2.setVisible(true);
                jt2.setVisible(true);
                b3=new JButton("Save");
                newPanel.add(b3);
                b3.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        jl.setText("Do you want to change your account?");
                        b2.setText("Change");
                        b1.setVisible(true);
                        message.setText("Userdata changed!");
                        jl1.setVisible(false);
                        jt1.setVisible(false);
                        jl2.setVisible(false);
                        jt2.setVisible(false);
                        b3.setVisible(false);
                        b1.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                                String userValue = textField1.getText();        
                                String passValue = textField2.getText();       
                                if(userValue.trim().equals(jt1.getText()) && passValue.trim().equals(jt2.getText())){
                                    message.setText("Exam Started..");
                                    new OnlineTestBegin(userValue);
                            } 
                            else{
                                message.setText("Invalid Userdata!");
                            }
                        }
                    }); 
                }
            }); 
                message.setText("Changing data...");
            }
        });  
        setTitle("Login Form ");         
    }   
    //Functionality of submit button making....
    public void actionPerformed(ActionEvent ae)     
    {  
        String userValue = textField1.getText();        
        String passValue = textField2.getText();       
        if(userValue.trim().equals(jt1.getText()) && passValue.trim().equals(jt2.getText())){
            message.setText("Exam Started..");
            new OnlineTestBegin(userValue);
        } 
        else{
            message.setText("Invalid Userdata!");
        }
    }     
} 
//Main exam frame making....
class OnlineTestBegin extends JFrame implements ActionListener  
{
    JLabel l,l1,jl;  
    JRadioButton jb[]=new JRadioButton[6];  
    JButton b1,b2,log;  
    ButtonGroup bg;  
    int count=0,current=0,x=1,y=1,now=0;  
    int m[]=new int[10];  
    Timer timer = new Timer();  
    OnlineTestBegin(String s)  
    {      
        super(s); 
        l=new JLabel();
        l1 = new JLabel();  
        add(l);
        add(l1);  
        bg=new ButtonGroup();  
        for(int i=0;i<5;i++)  
        {  
            jb[i]=new JRadioButton();     
            add(jb[i]);  
            bg.add(jb[i]);  
        }  
        b1=new JButton("Next");  
        b1.addActionListener(this);   
        add(b1);
        set();  
        l.setBounds(30,40,450,20);
        l1.setBounds(20,20,450,20);
        jb[0].setBounds(50,80,100,20);  
        jb[1].setBounds(50,110,100,20);  
        jb[2].setBounds(50,140,100,20);  
        jb[3].setBounds(50,170,100,20);  
        b1.setBounds(95,240,140,30);   
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLayout(null);  
        setLocation(250,100);  
        setVisible(true);  
        setSize(600,350);     
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 100;
            public void run() {  
                l1.setText("Time left: " + i);
                i--;   
                if (i < 0) {
                    timer.cancel();
                    l1.setText("Time Out");                     
                } 
            }
        }, 0, 1000);        
    }  
    public void actionPerformed(ActionEvent e)  
    {          
        if(e.getSource()==b1)  
        {  
            if(check())  
                count=count+1;  
            current++;  
            set();    
            if(current==9)  
            {  
                b1.setEnabled(false);    
                b2=new JButton("Result"); 
                add(b2);
                b2.setBounds(270,240,150,30);
                b2.addActionListener(this);
            }  
        }    
        if(e.getActionCommand().equals("Result"))  
        { 
            jl=new JLabel("Score="+count);
            add(jl);
            jl.setBounds(100,20,450,20); 
            b2.setText("LogOut"); 
        }  
        if(e.getActionCommand().equals("LogOut"))
            System.exit(0); 
    } 
    //Question Paper setting.....
    void set()  
    {  
        jb[4].setSelected(true);  
        if(current==0)  
        {  
            l.setText("Que1: Tsunamis are not caused by?");  
            jb[0].setText("Hurricanes");jb[1].setText("Earthquakes");jb[2].setText("Undersea landslides");jb[3].setText("Volcanic eruptions");   
        }  
        if(current==1)  
        {  
            l.setText("Que2:The country that has the highest in Barley Production?");  
            jb[0].setText("China");jb[1].setText("India");jb[2].setText("Russia");jb[3].setText("France");  
        }  
        if(current==2)  
        {  
            l.setText("Que3:Which one of the following rivers originates in Brahmagiri range of Western Ghats?");  
            jb[0].setText("Pennar");jb[1].setText("Cauvery");jb[2].setText("Krishna");jb[3].setText("None");  
        }  
        if(current==3)  
        {  
            l.setText("Que4:River Luni originates near Pushkar and drains into which one of the following?");  
            jb[0].setText("Rann of Kachchh");jb[1].setText("Arabian Sea");jb[2].setText("Gulf of Cambay");jb[3].setText("Lake Sambhar");  
        }  
        if(current==4)  
        {  
            l.setText("Que5:The metal whose salts are sensitive to light is?");  
            jb[0].setText("Zinc");jb[1].setText(" Silver");jb[2].setText("Copper");jb[3].setText("Aluminum");  
        }  
        if(current==5)  
        {  
            l.setText("Que6:Which among the following headstreams meets the Ganges in last?");  
            jb[0].setText("Alaknanda");jb[1].setText("Pindar");jb[2].setText("Mandakini");jb[3].setText("Bhagirathi");  
        }  
        if(current==6)  
        {  
            l.setText("Que7:Who among the following wrote Sanskrit grammar?");  
            jb[0].setText("Kalidasa");jb[1].setText("Charak");jb[2].setText("Panini");jb[3].setText("None");  
        }  
        if(current==7)  
        {  
            l.setText("Que8:The Central Rice Research Station is situated in?");  
            jb[0].setText("Chennai");jb[1].setText("Cuttack");jb[2].setText("Bangalore");jb[3].setText("None");         
        }  
        if(current==8)  
        {  
            l.setText("Que9:Which one of the following river flows between Vindhyan and Satpura ranges?");  
            jb[0].setText("Narmada");jb[1].setText("Mahanadi");jb[2].setText("Son");jb[3].setText("None");  
        }  
        if(current==9)  
        {  
            l.setText("Que10:Who invented JAVA programming?");  
            jb[0].setText("Dennis Ritchie");jb[1].setText("Tim Berners-Lee");jb[2].setText("James Gosling");jb[3].setText("Guido van Rossum");  
        }  
        l.setBounds(30,40,450,20);  
        for(int i=0,j=0;i<=90;i+=30,j++)  
            jb[j].setBounds(50,80+i,200,20);  
    }  
    //Answer setting....
    boolean check()  
    {  
        if(current==0)  
            return(jb[0].isSelected());  
        if(current==1)  
            return(jb[2].isSelected());  
        if(current==2)  
            return(jb[1].isSelected());  
        if(current==3)  
            return(jb[0].isSelected());  
        if(current==4)  
            return(jb[1].isSelected());  
        if(current==5)  
            return(jb[3].isSelected());  
        if(current==6)  
            return(jb[2].isSelected());  
        if(current==7)  
            return(jb[1].isSelected());  
        if(current==8)  
            return(jb[0].isSelected());  
        if(current==9)  
            return(jb[2].isSelected());  
        return false;  
    }    
} 
//main function calling....
public class OnlineExam  
{  
    public static void main(String args[])  
    {  
        try  
        {  
            login form = new login();  
            form.setSize(500,500);  
            form.setVisible(true);  
        }  
        catch(Exception e)  
        {     
            JOptionPane.showMessageDialog(null, e.getMessage());  
        }  
    }  
} 