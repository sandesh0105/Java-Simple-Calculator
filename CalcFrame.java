package SimpleCalc;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.event.*;

public class CalcFrame implements ActionListener{
    
    JFrame frame;
    JButton[] num_buttons,func_buttons;
    JPanel display,inputer;
    JLabel nums,funcs;
    JTextField write;


    public CalcFrame(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setLocationRelativeTo(frame);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setResizable(false);
        frame.setLayout(null);

        Border bord =  BorderFactory.createLineBorder(Color.BLUE,10);

        display = new JPanel();
        display.setBounds(0, 0, 785, 200);
        display.setBackground(Color.BLACK);
        display.setOpaque(true);
        display.setBorder(bord);
        display.setLayout(null);

        inputer = new JPanel();
        inputer.setBounds(0, 220, 785, 535);
        inputer.setBackground(Color.BLACK);
        inputer.setOpaque(true);
        inputer.setBorder(bord);
        inputer.setLayout(null);
        
        
        nums = new JLabel();
        nums.setBounds(0,0,500,530);
        nums.setBackground(Color.GREEN);
        nums.setOpaque(true);
        nums.setLayout(new GridLayout(4,3,10,10));
        nums.setBorder(bord);

        num_buttons = new JButton[10];

        for(int i = 0 ; i < 10 ; i++){
            num_buttons[i] = new JButton(""+i);
            num_buttons[i].setFocusable(false);
            num_buttons[i].setFont(new Font("Consolas",Font.BOLD,30));
            num_buttons[i].addActionListener(this);
        }

        for(int i = 0 ; i < 9 ; i++){
            nums.add(num_buttons[i+1]);
        }
        nums.add(num_buttons[0]);
        

        funcs = new JLabel();
        funcs.setBounds(490,0,295,530);
        funcs.setBackground(Color.RED);
        funcs.setOpaque(true);
        funcs.setLayout(new GridLayout(6,1,10,10));
        funcs.setBorder(bord);

        func_buttons = new JButton[7];

        func_buttons[0] = new JButton("CLR");
        func_buttons[0].setFont(new Font("Consolas",Font.BOLD,30));
        func_buttons[0].setFocusable(false);
        func_buttons[0].addActionListener(this);
        funcs.add(func_buttons[0]);
        
        func_buttons[1] = new JButton("<--");
        func_buttons[1].setFont(new Font("Consolas",Font.BOLD,30));
        func_buttons[1].setFocusable(false);
        func_buttons[1].addActionListener(this);
        funcs.add(func_buttons[1]);

        func_buttons[2] = new JButton("+");
        func_buttons[2].setFont(new Font("Consolas",Font.BOLD,30));
        func_buttons[2].setFocusable(false);
        func_buttons[2].addActionListener(this);
        funcs.add(func_buttons[2]);
        
        func_buttons[3] = new JButton("-");
        func_buttons[3].setFont(new Font("Consolas",Font.BOLD,30));
        func_buttons[3].setFocusable(false);
        func_buttons[3].addActionListener(this);
        funcs.add(func_buttons[3]);

        func_buttons[4] = new JButton("*");
        func_buttons[4].setFont(new Font("Consolas",Font.BOLD,30));
        func_buttons[4].setFocusable(false);
        func_buttons[4].addActionListener(this);
        funcs.add(func_buttons[4]);

        func_buttons[5] = new JButton("/");
        func_buttons[5].setFont(new Font("Consolas",Font.BOLD,30));
        func_buttons[5].setFocusable(false);
        func_buttons[5].addActionListener(this);
        funcs.add(func_buttons[5]);

        func_buttons[6] = new JButton("=");
        func_buttons[6].setFont(new Font("Consolas",Font.BOLD,30));
        func_buttons[6].setFocusable(false);
        func_buttons[6].addActionListener(this);
        funcs.add(func_buttons[6]);

        write = new JTextField();
        write.setBounds(0, 0, 785, 200);
        write.setFont(new Font("Consolas",Font.BOLD,200));
        write.setBorder(bord);
        write.setEditable(true);
        write.setForeground(Color.GREEN);
        write.setBackground(Color.BLACK);
        write.setOpaque(true);
        write.setVisible(true);
        write.setCaretColor(Color.WHITE);

        display.add(write);

        inputer.add(nums);
        inputer.add(funcs);

        frame.add(display);
        frame.add(inputer);
        frame.setVisible(true);
    }

public boolean isNum(String str){
    try {
        Integer.parseInt(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

public int result(int a , int b , String op){
    switch(op){
        case("+"):{
            return a+b;
        }
        case("-"):{
            return a-b;
        }
        case("*"):{
            return a*b;
        }
        case("/"):{
            return a/b;
        }
        default:{
            System.out.println("wrong op nigga");
            return 0 ;
        }
    }
}

public void resize(){
    //Get the width of the text
    FontMetrics fm = write.getFontMetrics(write.getFont());
    int textWidth = fm.stringWidth(write.getText());

    // Get the width of the JTextField
    int fieldWidth = write.getWidth();

    // Decrease font size if text width exceeds field width
    while (textWidth > fieldWidth && write.getFont().getSize() > 10) {
        Font currentFont = write.getFont();
        int newSize = currentFont.getSize() - 10;
        write.setFont(new Font(currentFont.getName(), currentFont.getStyle(), newSize));
        fm = write.getFontMetrics(write.getFont());
        textWidth = fm.stringWidth(write.getText());
    }
}


@Override
public void actionPerformed(ActionEvent e) {
    try {
        int index = 0 ;
        while(index<17){
            if(index<7){
                if(e.getSource()==func_buttons[index])
                    break;
                else
                    index++;
            }
            else{
                if(e.getSource()==num_buttons[index-7])
                    break;
                else
                    index++;
            }
        }//bohot ganda code hai but aur kuch sujh nahi raha tha toh
    
    
     if(index!=0&&index!=1){
        write.setText(write.getText()+( index<7 ? func_buttons[index].getText() : num_buttons[index-7].getText() ));

        if(write.getCaretPosition() == write.getText().length()){
            resize();
        }

        Integer operand1,operand2;
        String operator = "";

        operand1 = 0;
        operand2 = 0;
            
        if(index == 6){
            String operation = write.getText();
            int i ;
            for(i = 0 ;i<operation.length(); i++){
                if(!isNum(String.valueOf(operation.charAt(i)))){
                    break;
                }
            }
            operand1 = Integer.parseInt(operation.substring(0, i));
            operand2 = Integer.parseInt(operation.substring(i+1, operation.length()-1));
            operator = String.valueOf(operation.charAt(i));
            
            Integer res = result(operand1, operand2, operator);
            operand1 = res;
            write.setText(String.valueOf(operand1));
        }
        //clear text after this
        
     }
     else if (index == 1) {
        String text = write.getText();
        write.setText(write.getText(0,text.length()-1));
     }
     else if(index == 0){
        write.setText(null);
     }
    }
    catch (Exception j) {
        System.out.println("Please enter a valid input!! "+j);
    }   
 }

}
