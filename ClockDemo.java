package biao;
import java.awt.*;
import java.text.DateFormat;
import java.util.*;
import javax.swing.*;
//����ʱ��
public class ClockDemo extends JFrame implements Runnable
{
  Thread clock;
  final int Xpoint=180;
  final int Ypoint=180;
  final int R=80;
  int xHour=0,yHour=0,xSecond=0,ySecond=0,xMin=0,yMin=0;
  public ClockDemo()
  {
     super("����ʱ��"); //���ø��๹�캯��
     setFont(new Font("����",Font.BOLD,20)); //����ʱ�ӵ���ʾ����
     start(); //��ʼ����
     setSize(360,360);  //���ô��ڳߴ�
     setVisible(true);  //���ڿ���
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
 }
public void start()
 { //��ʼ����
   if(clock==null)//�������Ϊ��ֵ
   {
      clock=new Thread(this); //ʵ��������
      clock.start(); //��ʼ����
   }
 }
public void run()//���н���
{ 
   while (clock!=null)
   {
      repaint(); //����paint�����ػ����
      try
      {
        Thread.sleep(1000);  //�߳���ͣһ��(1000����)
      }
      catch (InterruptedException ex){
      ex.printStackTrace();  //���������Ϣ
   }
  }
 }
public void stop()//ֹͣ����
{ 
   clock=null;
}
public void paint(Graphics g)//���������paint����
{ 
   Graphics2D g2=(Graphics2D)g;  //�õ�Graphics2D����
   DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
   Calendar now=new GregorianCalendar(); //ʵ������������
   now.setTime(new Date());//dateFormat.format(now.getTime())
   String timeInfo=""; //�����Ϣ
   int hour=now.get(Calendar.HOUR_OF_DAY); //�õ�Сʱ��
   int minute=now.get(Calendar.MINUTE);   //�õ�����
   int second=now.get(Calendar.SECOND);  //�õ�����
   if (hour<=9)
      timeInfo+="0"+hour+":"; //��ʽ�����
   else
      timeInfo+=hour+":";
   if (minute<=9)
      timeInfo+="0"+minute+":";
   else
      timeInfo+=minute+":";
   if (second<=9)
      timeInfo+="0"+second;
   else
       timeInfo+=second;
   g.setColor(Color.yellow);  //���õ�ǰ��ɫΪ��ɫ
   Dimension dim=getSize();  //�õ����ڳߴ�
   g.fillRect(0,0,dim.width,dim.height);  //��䱳��ɫΪ��ɫ
   g.setColor(Color.red);  //���õ�ǰ��ɫΪ��ɫ
   g.drawString(timeInfo,130,340);  //��ʾʱ���ַ���
   g.setColor(Color.green);
   g.drawString(dateFormat.format(now.getTime()),20,60);
   g.setColor(Color.black);
   g.setFont(new Font("SAN_SERIF",Font.BOLD,15));
 for(int i=0,num=12;i<360;i+=6)
{ 
 double alfa = Math.toRadians(i);
 int xPos=Xpoint+(int)(R*Math.sin(alfa));
 int yPos=Ypoint-(int)(R*Math.cos(alfa));
 if(i==0)
 {
  if (num%3==0)
   g.setColor(Color.red);  //  ����3,6,9,12Ϊ��ɫ
  else
   g.setColor(Color.black);  // ��������Ϊ��ɫ
  g.drawString(""+num,xPos-5,yPos+3);  // д����
  num=(num+1); 
 }
 else
 {
  g.setColor(Color.black);
  g.drawString(".",xPos,yPos);
 }
}
 g.setColor(Color.black);
 g.fillOval(Xpoint-4,Ypoint-4,8,8);
 //������
    xSecond=(int)(Xpoint+(R-10)*Math.sin(second*(2*Math.PI/60)));
    ySecond=(int)(Ypoint-(R-10)*Math.cos(second*(2*Math.PI/60)));
    g.setColor(Color.red);
    g.drawLine(Xpoint,Ypoint,xSecond,ySecond);
    //������
    xMin=(int)(Xpoint+(R-20)*Math.sin((minute+second/60)*(2*Math.PI/60)));
    yMin=(int)(Ypoint-(R-20)*Math.cos((minute+second/60)*(2*Math.PI/60)));
    g.setColor(Color.red);
    g.drawLine(Xpoint,Ypoint,xMin,yMin);
    //��ʱ��
    xHour=(int)(Xpoint+(R-30)*Math.sin((hour+minute/60+second/60/60)*(2*Math.PI/12)));
    yHour=(int)(Ypoint-(R-30)*Math.cos((hour+minute/60+second/60/60)*(2*Math.PI/12)));
    g.setColor(Color.red);
    g.drawLine(Xpoint,Ypoint,xHour,yHour);
}
public static void main(String[] args)
{
  new ClockDemo();
}
}