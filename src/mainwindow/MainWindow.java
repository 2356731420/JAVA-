package mainwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.RandomAccessFile;

public class MainWindow extends JFrame {
    int a[]=new int[15],b[]=new int[15],c[]=new int[15];//������������a,b,c
    JTextField ans[]=new JTextField[15];//�����ı��򼰴���дλ��
    JButton start = new JButton("����");//�������ť
    JButton submit = new JButton("����");//���彻��ť
    JButton FirstB = new JButton("����");//���崴����ť
    JLabel RP = new JLabel();//����ɼ���ǩ
    JLabel File_Report[] = new JLabel[10];//��������������ȷ�ʵı�ǩ
    JLabel s1[] = new JLabel[15];//�����ǩ����s1���汻����
    JLabel s2[] = new JLabel[15];//�����ǩ����s2�������
    JLabel s3[] = new JLabel[15];//�����ǩ����s3��������� + or -
    JLabel s4[] = new JLabel[15];//�����ǩ����s4����Ⱥ�
    Container container = getContentPane();//����һ��container�������洢����ʽ���ñ�ǩ����ť
    File file = new File("");//����һ���ı�


    void swap(int x,int y,int i){
        if(x<y){//��������С�ڼ���ʱִ�и��������
            this.a[i]=y;//ֱ�Ӹ���this�����ԺͲ������������Խ���
            this.b[i]=x;//ֱ�Ӹ���this�����ԺͲ������������Խ���
        }
    }//�����������ͼ����ĺ���
    String charx(){//��������
        int x=figure();//���������
        if(x%2==0){
            return "��";//��xΪż��ʱ���ؼӺ�
        }
        else{
            return "��";//��xΪ����ʱ���ؼ���
        }
    }//��������ɺ���
    public int figure(){
        int MAX= 100,MIN =10;//��ʼ����
        int ran=(int)(Math.random()*(MAX-MIN)+MIN);//�ڶ��������������ʽ
        return ran;//�������ɵ�2λ�����
    }//��λ��������ɺ���
    void check(int i,String s){
        if(s=="��") {//��������ǼӺ�ʱ�Ż����x+y>=100����Сѧ1��2�꼶ˮƽ����������ϸ����ḻ���
            for (int j = 1; j <= 2; j++) {//�������η�ֹ���еĴ�����ּ���50֮�������ֵĺ����ɴ���100 ����90+90
                if (a[i] + b[i] >= 100) {//��x+y>=100ʱ���������޸����
                    a[i] -= 50;//��������ȥ50
                    swap(a[i], b[i], i);//����������ȥ50����ܻ���ֱ�����С�ڼ���������������ڳ��ַ��ţ����Ե���swap��������
                }
                else break;//��x+y<100ʱ��ֱ������ѭ��
            }
        }
    }//���麯���������Ƿ�����Ϊ�ӷ�ʱx+y��
    void makeLAB(){
        for(int i=1;i<=10;i++){
            a[i]=figure();//������
            b[i]=figure();//����
            swap(a[i],b[i],i);//��֤����Ǽ���ʱ�Ƿ�Ϊ������ֱ�ӽ���
            s1[i]=new JLabel();//����s1[i]
            s2[i]=new JLabel();//����s2[i]
            s3[i]=new JLabel();//����s3[i]
            s4[i]=new JLabel();//����s4[i]
            String s=charx();//ͨ��������õ�������� + or -
            s3[i].setText(s);//��ͨ��������õ�������� + or - ��ֵ��s3[i]��ǩ��
            check(i,s);//�����ļ�x+y�Ƿ����100����Сѧ1��2�꼶ˮƽ
            String C1=String.valueOf(a[i]);//��a[i]ת����string��
            String C2=String.valueOf(b[i]);//��b[i]ת����string��
            s1[i].setText(C1);//��ת����a[i]��ֵ��s1[i]��ǩ��
            s2[i].setText(C2);//��ת����b[i]��ֵ��s2[i]��ǩ��
            s4[i].setText("��");//���ȺŸ�ֵ��s4[i]��ǩ��
        }
    }//��������������������͵Ⱥ����ɲ���ֵ����ǩ�еĺ���
    void changLAB(){
        for(int i=1;i<=10;i++){
            a[i]=figure();//������
            b[i]=figure();//����
            swap(a[i],b[i],i);//��֤����Ǽ���ʱ�Ƿ�Ϊ������ֱ�ӽ���
            String s=charx();//ͨ��������õ�������� + or -
            s3[i].setText(s);//��ͨ��������õ�������� + or - ��ֵ��s3[i]��ǩ��
            check(i,s);//�����ļ�x+y�Ƿ����100����Сѧ1��2�꼶ˮƽ
            String C1=String.valueOf(a[i]);//��a[i]ת����string��
            String C2=String.valueOf(b[i]);//��b[i]ת����string��
            s1[i].setText(C1);//��ת����a[i]��ֵ��s1[i]��ǩ��
            s2[i].setText(C2);//��ת����b[i]��ֵ��s2[i]��ǩ��
            s4[i].setText("��");//���ȺŸ�ֵ��s4[i]��ǩ��
        }
    }//���ı�ǩ�е����ֺ������
    void cleanJT(){
        for(int i=1;i<=10;i++){
            ans[i].setText("");//��մ��ı��������
            ans[i].setForeground(new Color(0,0,0));//�ô��ı����������ɫ��ɺ�ɫ
        }
        RP.setText("");//��ճɼ���ǩ������
    }//��մ𰸺ͳɼ��ĺ���
    ActionListener START=new ActionListener(){
        @Override//������д
        public void actionPerformed(ActionEvent e1){//��ť����ʱ��e1
            changLAB();//���ø��ı�ǩ�е����ֺ�������ĺ���
            cleanJT();//������մ��ı���ͳɼ���ǩ�ĺ���
        }
    };//���ð�ť�����¼�
    String conculater(int x,int y,String s){
        if(s=="��"){//�������Ϊ�Ӻ�ʱִ�и����
            String cs=(x+y)+"";//�ӷ��������ǿ��ת��ΪString��
            return cs;//���ؽ��
        }
        else{
            String cs=(x-y)+"";//�����������ǿ��ת��ΪString��
            return cs;//���ؽ��
        }
    }//���㺯��
    void report(int sums){//���������Ե���Ŀ����
        RP.setFont(new Font("",Font.BOLD,100));//����ɼ���ǩ�������С
        RP.setForeground(new Color(255,0,0));//����ɼ���ǩ�������ɫ
        setLayout(null);//��ղ��ֹ�����
        RP.setBounds(1000,25,100,110);//���óɼ���ǩ��λ�úʹ�С
        container.add(RP);//���ɼ���ǩ��������
        File file1 = new File("D:\\Report1.dat");//���ҳɼ������ĵ�λ��
        RandomAccessFile inAndOUT = null;//
        double data[]={0,0,0};//�������飬�����ĵ��Ķ�ȡ����
        try{
            inAndOUT = new RandomAccessFile(file1,"rw");//�����µ��������������ɶ���д
        }catch (Exception e){}
        try{
            for(int i=0;i<=data.length-1;i++){
                inAndOUT.seek(i*8);//double����8�ֽ�
                data[i]=inAndOUT.readDouble();//���µõ���double����
            }
            inAndOUT.close();//�ر����������
            data[0]+=10;//��������10����Ŀ
            data[1]=data[1]+10-sums/10;//����������10-����/10
            File_Report[2].setText((int)data[0]+"");//���ı�ǩ����������
            File_Report[4].setText((int)data[1]+"");//���ı�ǩ�Ĵ�����
            data[2]=((data[0]-data[1])/data[0])*100;//������ȷ��
            data[2]=(int)(data[2]*100+0.5);
            data[2]=data[2]/100;//����С�������λ
            File_Report[6].setText(data[2]+"%");//���ı�ǩ����ȷ��
        }catch (Exception e){}
        file1.delete();//ɾ���ĵ�
        try{
            inAndOUT = new RandomAccessFile(file1,"rw");//�����µ��������������ɶ���д
            for(int i=0;i<=data.length-1;i++){
                inAndOUT.writeDouble(data[i]);//���µ�����д���ĵ�
            }
            //System.out.println(file1.length());//�鿴�ĵ�����
            inAndOUT.close();//�ر����������
        }catch (Exception e){}
        if(sums>=90){//��������90ִ�и����
            RP.setText("A");//�ɼ���ǩ��������ΪA
            return ;//����
        }
        if(sums>=80){//��������80ִ�и����
            RP.setText("B");//�ɼ���ǩ��������ΪB
            return ;
        }
        if(sums>=70){//��������70ִ�и����
            RP.setText("C");//�ɼ���ǩ��������ΪC
            return ;
        }
        if(sums>=60){//��������60ִ�и����
            RP.setText("D");//�ɼ���ǩ��������ΪD
            return ;
        }
        if(sums<60){//����С��60ִ�и����
            RP.setText("E");//�ɼ���ǩ��������ΪE
            return ;//����
        }
    }//�ɼ����㺯��
    void checkans(){
        int sums = 0;//�ɼ�
        int sans;//��ǰ���ı����еĽ��
        for(int i=1;i<=10;i++){
            String s=ans[i].getText();//��ȡ���ı���
            int sl=s.length();//�õ����ı������ַ����ĳ���
            if(sl!=0)//������ı�����ַ������Ȳ�Ϊ0�������ı������д�
                sans=Integer.parseInt(s);//ǿ��ת���ַ���Ϊ������
            else{
                sans=-1;//��֮sansΪ-1�����ı���û�д�
            }
            int cans=Integer.parseInt(conculater(a[i],b[i],s3[i].getText()));//ǿ��ת������ʽ�Ľ����String�ͱ��int��
            if(sans!=cans){//���𰸲�ͬʱ
                ans[i].setForeground(new Color(255,0,0));//�ô��ı�������ֱ�Ϊ��ɫ
                ans[i].setText(cans+"");//�Ѵ��ı���Ĵ𰸻�����ȷ��
            }
            else{//������ͬʱ
                sums+=10;//��һ���⣬�ɼ���10
            }
        }
        report(sums);//���óɼ����㺯��
    }//�𰸳���
    ActionListener SUBMIT=new ActionListener() {
        @Override//������д
        public void actionPerformed(ActionEvent e2) {//���尴ť�����¼�e2
            checkans();///���ô𰸾����ͼ���ɼ��ĺ���
        }
    };//����ť����¼�
    void setLAB(){
        for(int i=1;i<=10;i++){
            setLayout(null);//��ղ��ֹ�����
            s1[i].setBounds(350,120+(i-1)*55,100,50);//���ñ�������ǩ��λ��
            s2[i].setBounds(490,120+(i-1)*55,100,50);//���ü�����ǩ��λ��
            s3[i].setBounds(420,120+(i-1)*55,100,50);//�����������λ��
            s4[i].setBounds(560,120+(i-1)*55,100,50);//���õȺŵ�λ��
            s1[i].setFont(new Font("",Font.BOLD,50));//s1[i]��ǩ���ֵĴ�С��ɫ
            s2[i].setFont(new Font("",Font.BOLD,50));//s2[i]��ǩ���ֵĴ�С��ɫ
            s3[i].setFont(new Font("",Font.BOLD,50));//s3[i]��ǩ���ֵĴ�С��ɫ
            s4[i].setFont(new Font("",Font.BOLD,50));//s4[i]��ǩ���ֵĴ�С��ɫ
            container.add(s1[i]);//�ѱ�������ǩ��������
            container.add(s2[i]);//�Ѽ�����ǩ��������
            container.add(s3[i]);//���������ǩ��������
            container.add(s4[i]);//�ѵȺű�ǩ��������
        }
    }//������ֵ�ı�ǩ���������С����봰��ĺ���
    void setJText(){
        for(int i=1;i<=10;i++){
            setLayout(null);//��ղ��ֹ�����
            ans[i]= new JTextField("");//�����ı���
            ans[i].setBounds(650,120+(i-1)*55,100,50);//�����ı���λ��
            ans[i].setFont(new Font("",Font.BOLD,50));//�����ı������ִ�С
            container.add(ans[i]);//�����ı���
        }
    }//����ı�����뺯��
    void setBOT(){
        setLayout(null);//��ղ��ֹ�����
        start.setFont(new Font("",Font.BOLD,35));//����ť�����ִ�С
        submit.setFont(new Font("",Font.BOLD,35));//����ť�����ִ�С
        start.setBounds(900,650,110,60);//����ť��λ�úͰ�ť��С
        submit.setBounds(900,720,110,60);//����ť��λ�úͰ�ť��С
        start.addActionListener(START);//������ť��������¼�
        submit.addActionListener(SUBMIT);//������ť��������¼�
        container.add(start);//������ť��������
        container.add(submit);//������ť��������
    }//��ť���뺯��
    void FILEBUILD(){
        file = new File("D:/","Report1.dat");//�����µĴ洢���ݵ��ı�
        RandomAccessFile inAndOUT = null;
        double data[]={0,0,0};//��������洢����
        try{
            inAndOUT = new RandomAccessFile(file,"rw");//�������������
        }catch (Exception e){}
        try{
            for(int i=0;i<=data.length-1;i++){
                inAndOUT.writeDouble(data[i]);//����ʼ�����������ļ�
            }
            File_Report[2].setText((int)data[0]+"");//����������ǩ��ֵ
            File_Report[4].setText((int)data[1]+"");//����������ǩ��ֵ
            File_Report[6].setText(data[2]+"%");//����ȷ�ʱ�ǩ��ֵ
            inAndOUT.close();//�ر����������
        }catch (Exception e){}
    }//��ʼ���ú���
    void file_Build(){
        File file = new File("D:\\Report1.dat");//�����ļ�
        if(file.exists()){//�ļ�����ִ�и�if���
            //System.out.println("213");
            RandomAccessFile inAndOUT = null;
            double data[]={0,0,0};//�������� �洢�ļ�����
            try{
                inAndOUT = new RandomAccessFile(file,"rw");//������������� �ɶ���д
            }catch (Exception e){}
            try{
                for(int i=0;i<=data.length-1;i++){
                    inAndOUT.seek(i*8);//doubleΪ8�ֽ�
                    data[i]=inAndOUT.readDouble();//��ȡ�ĵ����ݸ�ֵ��data[i]
                }
                File_Report[2].setText((int)data[0]+"");//���������ı�ǩ���ָ�ֵ
                File_Report[4].setText((int)data[1]+"");//���������ı�ǩ���ָ�ֵ
                if(data[0]!=0) {
                    data[2] = ((data[0] - data[1]) / data[0]) * 100;//���ĵ��е���������Ϊ0ʱ��ֱ�Ӽ�����ȷ��
                    data[2] = (int) (data[2] * 100);
                    data[2] = data[2] / 100;//���ñ���С�������λ
                }
                else{
                    data[2]=0.0;//��������Ϊ0ʱֱ��������ȷ��Ϊ0��ֹ/0�õ�NANֵ
                }
                File_Report[6].setText(data[2]+"%");//����ȷ�ʵı�ǩ���ָ�ֵ
                inAndOUT.close();//�ر����������
            }catch (Exception e){}
        }
    }//������������������ȷ�ʵĶ�ȡ���뺯��
    void setFile_Report(){
        for(int i=1;i<=6;i++){
            File_Report[i]=new JLabel();
            File_Report[i].setBounds(150+(i-1)*110,700,130,60);
            File_Report[i].setFont(new Font("",Font.BOLD,32));
            container.add(File_Report[i]);
        }
        File_Report[1].setText("������:");
        File_Report[3].setText("������:");
        File_Report[5].setText("��ȷ��:");
    }
    ActionListener FIRSTBuild = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e3) {
            FILEBUILD();//�����һ��ʹ��ʱ���е��ļ�
        }
    };//���崴����ť�ļ����¼�
    void setBOTF(){
        setLayout(null);//��ղ��ֹ�����
        FirstB.setFont(new Font("",Font.BOLD,35));//���ô�����ť�����ִ�С
        FirstB.setBounds(900,790,110,60);//���ô�����ť��λ��
        FirstB.addActionListener(FIRSTBuild);//�������¼�FIRSTBuild���봴����ť
        container.add(FirstB);//���봴����ť������
    }//���봴����ť
    public MainWindow(String s){
        JLabel title = new JLabel("��ӭʹ�üӼ�������Сϵͳ");//���ñ�ǩ����
        JLabel First_use = new JLabel("������ǵ�һ��ʹ�øó������ȵ��������ť��");//���õ�һ��ʹ����ʾ��ǩ����
        First_use.setBounds(85,790,1200,60);
        First_use.setFont(new Font("",Font.BOLD,35));
        title.setFont(new Font("",Font.BOLD,45));//��ֻ��ǩ���ִ�С����
        title.setForeground(new Color(255,0,0));//������ɫ
        setTitle(s);//���ڱ���
        setLocation(375,55);//����λ��
        setSize( 1200, 950);//����Ĵ�С
        Container containertitle =getContentPane();//����һ�������������
        title.setBounds(300,0,3000,150);//���ñ����λ��
        containertitle.add(title);//����ǩ��������
        containertitle.add(First_use);//�����һ��ʹ����ʾ��ǩ
        setFile_Report();//��������������ȷ�ʱ�ǩ
        file_Build();//����������������������ȷ�ʵĶ�ȡ���뺯��
        makeLAB();//���ñ�������������������͵Ⱥ����ɲ���ֵ����ǩ�еĺ���
        setLAB();//���ðѱ�������������������͵Ⱥű�ǩ�ķ��������ĺ���
        setBOT();//���ðѰ�ť�����վ���������ĺ���
        setBOTF();//���ô�����ť���뺯��
        setJText();//���ðѴ��ı�����������ĺ���
        setVisible(true);//����ɼ�
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�������Ͻǹرհ���ִ�к���ֱ�ӹرճ���
    }
}