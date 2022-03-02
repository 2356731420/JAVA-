package mainwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.RandomAccessFile;

public class MainWindow extends JFrame {
    int a[]=new int[15],b[]=new int[15],c[]=new int[15];//定义整数数组a,b,c
    JTextField ans[]=new JTextField[15];//定义文本框及答案书写位置
    JButton start = new JButton("出卷");//定义出卷按钮
    JButton submit = new JButton("交卷");//定义交卷按钮
    JButton FirstB = new JButton("创建");//定义创建按钮
    JLabel RP = new JLabel();//定义成绩标签
    JLabel File_Report[] = new JLabel[10];//定义做题量、正确率的标签
    JLabel s1[] = new JLabel[15];//定义标签数组s1储存被减数
    JLabel s2[] = new JLabel[15];//定义标签数组s2储存减数
    JLabel s3[] = new JLabel[15];//定义标签数组s3储存运算符 + or -
    JLabel s4[] = new JLabel[15];//定义标签数组s4储存等号
    Container container = getContentPane();//创建一个container容器，存储算术式所用标签、按钮
    File file = new File("");//创建一个文本


    void swap(int x,int y,int i){
        if(x<y){//当被减数小于减数时执行该条件语句
            this.a[i]=y;//直接根据this的特性和参数变量的特性交换
            this.b[i]=x;//直接根据this的特性和参数变量的特性交换
        }
    }//交换被减数和减数的函数
    String charx(){//符号生成
        int x=figure();//生成随机数
        if(x%2==0){
            return "＋";//当x为偶数时返回加号
        }
        else{
            return "－";//当x为奇数时返回减号
        }
    }//运算符生成函数
    public int figure(){
        int MAX= 100,MIN =10;//初始定义
        int ran=(int)(Math.random()*(MAX-MIN)+MIN);//第二种随机数生产方式
        return ran;//返回生成的2位随机数
    }//两位随机数生成函数
    void check(int i,String s){
        if(s=="＋") {//当运算符是加号时才会出现x+y>=100超出小学1、2年级水平的情况，加上该语句丰富题库
            for (int j = 1; j <= 2; j++) {//查验两次防止其中的大的数字减掉50之后两数字的和依旧大于100 例如90+90
                if (a[i] + b[i] >= 100) {//当x+y>=100时运行数字修改语句
                    a[i] -= 50;//被减数减去50
                    swap(a[i], b[i], i);//当被减数减去50后可能会出现被减数小于减数的情况，以至于出现符号，所以调用swap交换函数
                }
                else break;//当x+y<100时，直接跳出循环
            }
        }
    }//查验函数，查验是否运算为加法时x+y的
    void makeLAB(){
        for(int i=1;i<=10;i++){
            a[i]=figure();//被减数
            b[i]=figure();//减数
            swap(a[i],b[i],i);//验证如果是减法时是否为负并且直接交换
            s1[i]=new JLabel();//构造s1[i]
            s2[i]=new JLabel();//构造s2[i]
            s3[i]=new JLabel();//构造s3[i]
            s4[i]=new JLabel();//构造s4[i]
            String s=charx();//通过随机数得到的运算符 + or -
            s3[i].setText(s);//将通过随机数得到的运算符 + or - 赋值到s3[i]标签中
            check(i,s);//查验文件x+y是否大于100超过小学1、2年级水平
            String C1=String.valueOf(a[i]);//把a[i]转换成string形
            String C2=String.valueOf(b[i]);//把b[i]转换成string形
            s1[i].setText(C1);//将转换的a[i]赋值到s1[i]标签中
            s2[i].setText(C2);//将转换的b[i]赋值到s2[i]标签中
            s4[i].setText("＝");//讲等号赋值到s4[i]标签中
        }
    }//被减数、减数、运算符和等号生成并赋值到标签中的函数
    void changLAB(){
        for(int i=1;i<=10;i++){
            a[i]=figure();//被减数
            b[i]=figure();//减数
            swap(a[i],b[i],i);//验证如果是减法时是否为负并且直接交换
            String s=charx();//通过随机数得到的运算符 + or -
            s3[i].setText(s);//将通过随机数得到的运算符 + or - 赋值到s3[i]标签中
            check(i,s);//查验文件x+y是否大于100超过小学1、2年级水平
            String C1=String.valueOf(a[i]);//把a[i]转换成string形
            String C2=String.valueOf(b[i]);//把b[i]转换成string形
            s1[i].setText(C1);//将转换的a[i]赋值到s1[i]标签中
            s2[i].setText(C2);//将转换的b[i]赋值到s2[i]标签中
            s4[i].setText("＝");//讲等号赋值到s4[i]标签中
        }
    }//更改标签中的数字和运算符
    void cleanJT(){
        for(int i=1;i<=10;i++){
            ans[i].setText("");//清空答案文本框的内容
            ans[i].setForeground(new Color(0,0,0));//让答案文本框的文字颜色变成黑色
        }
        RP.setText("");//清空成绩标签的内容
    }//清空答案和成绩的函数
    ActionListener START=new ActionListener(){
        @Override//方法重写
        public void actionPerformed(ActionEvent e1){//按钮监视时间e1
            changLAB();//调用更改标签中的数字和运算符的函数
            cleanJT();//调用清空答案文本框和成绩标签的函数
        }
    };//设置按钮监视事件
    String conculater(int x,int y,String s){
        if(s=="＋"){//当运算符为加号时执行该语句
            String cs=(x+y)+"";//加法运算完后强制转换为String形
            return cs;//返回结果
        }
        else{
            String cs=(x-y)+"";//减法运算玩后强制转换为String形
            return cs;//返回结果
        }
    }//计算函数
    void report(int sums){//参数是做对的题目数量
        RP.setFont(new Font("",Font.BOLD,100));//定义成绩标签的字体大小
        RP.setForeground(new Color(255,0,0));//定义成绩标签字体的颜色
        setLayout(null);//清空布局管理器
        RP.setBounds(1000,25,100,110);//设置成绩标签的位置和大小
        container.add(RP);//将成绩标签放入容器
        File file1 = new File("D:\\Report1.dat");//查找成绩保存文档位置
        RandomAccessFile inAndOUT = null;//
        double data[]={0,0,0};//设置数组，保存文档的读取内容
        try{
            inAndOUT = new RandomAccessFile(file1,"rw");//设置新的输入输入流，可读可写
        }catch (Exception e){}
        try{
            for(int i=0;i<=data.length-1;i++){
                inAndOUT.seek(i*8);//double类型8字节
                data[i]=inAndOUT.readDouble();//存下得到的double数字
            }
            inAndOUT.close();//关闭输入输出流
            data[0]+=10;//交卷做题10道题目
            data[1]=data[1]+10-sums/10;//错题数等于10-分数/10
            File_Report[2].setText((int)data[0]+"");//更改标签的做题数字
            File_Report[4].setText((int)data[1]+"");//更改标签的错题数
            data[2]=((data[0]-data[1])/data[0])*100;//计算正确率
            data[2]=(int)(data[2]*100+0.5);
            data[2]=data[2]/100;//保留小数点后两位
            File_Report[6].setText(data[2]+"%");//更改标签的正确率
        }catch (Exception e){}
        file1.delete();//删除文档
        try{
            inAndOUT = new RandomAccessFile(file1,"rw");//设置新的输入输入流，可读可写
            for(int i=0;i<=data.length-1;i++){
                inAndOUT.writeDouble(data[i]);//把新的数据写入文档
            }
            //System.out.println(file1.length());//查看文档长度
            inAndOUT.close();//关闭输入输出流
        }catch (Exception e){}
        if(sums>=90){//分数大于90执行该语句
            RP.setText("A");//成绩标签内容设置为A
            return ;//返回
        }
        if(sums>=80){//分数大于80执行该语句
            RP.setText("B");//成绩标签内容设置为B
            return ;
        }
        if(sums>=70){//分数大于70执行该语句
            RP.setText("C");//成绩标签内容设置为C
            return ;
        }
        if(sums>=60){//分数大于60执行该语句
            RP.setText("D");//成绩标签内容设置为D
            return ;
        }
        if(sums<60){//分数小于60执行该语句
            RP.setText("E");//成绩标签内容设置为E
            return ;//返回
        }
    }//成绩计算函数
    void checkans(){
        int sums = 0;//成绩
        int sans;//当前答案文本框中的结果
        for(int i=1;i<=10;i++){
            String s=ans[i].getText();//读取答案文本框
            int sl=s.length();//得到答案文本框里字符串的长度
            if(sl!=0)//如果答案文本框的字符串长度不为0，即答案文本框里有答案
                sans=Integer.parseInt(s);//强制转换字符串为整数型
            else{
                sans=-1;//反之sans为-1即答案文本框没有答案
            }
            int cans=Integer.parseInt(conculater(a[i],b[i],s3[i].getText()));//强制转换计算式的结果从String型变成int型
            if(sans!=cans){//当答案不同时
                ans[i].setForeground(new Color(255,0,0));//让答案文本框的文字变为红色
                ans[i].setText(cans+"");//把答案文本框的答案换成正确答案
            }
            else{//当答案相同时
                sums+=10;//对一道题，成绩加10
            }
        }
        report(sums);//调用成绩计算函数
    }//答案程序
    ActionListener SUBMIT=new ActionListener() {
        @Override//方法重写
        public void actionPerformed(ActionEvent e2) {//定义按钮监视事件e2
            checkans();///调用答案纠正和计算成绩的函数
        }
    };//交卷按钮监控事件
    void setLAB(){
        for(int i=1;i<=10;i++){
            setLayout(null);//清空布局管理器
            s1[i].setBounds(350,120+(i-1)*55,100,50);//设置被减数标签的位置
            s2[i].setBounds(490,120+(i-1)*55,100,50);//设置减数标签的位置
            s3[i].setBounds(420,120+(i-1)*55,100,50);//设置运算符的位置
            s4[i].setBounds(560,120+(i-1)*55,100,50);//设置等号的位置
            s1[i].setFont(new Font("",Font.BOLD,50));//s1[i]标签文字的大小颜色
            s2[i].setFont(new Font("",Font.BOLD,50));//s2[i]标签文字的大小颜色
            s3[i].setFont(new Font("",Font.BOLD,50));//s3[i]标签文字的大小颜色
            s4[i].setFont(new Font("",Font.BOLD,50));//s4[i]标签文字的大小颜色
            container.add(s1[i]);//把被减数标签放入容器
            container.add(s2[i]);//把减数标签放入容器
            container.add(s3[i]);//把运算符标签放入容器
            container.add(s4[i]);//把等号标签放入容器
        }
    }//将被赋值的标签调整字体大小后放入窗体的函数
    void setJText(){
        for(int i=1;i<=10;i++){
            setLayout(null);//清空布局管理器
            ans[i]= new JTextField("");//构造文本框
            ans[i].setBounds(650,120+(i-1)*55,100,50);//设置文本框位置
            ans[i].setFont(new Font("",Font.BOLD,50));//设置文本框文字大小
            container.add(ans[i]);//插入文本框
        }
    }//结果文本框插入函数
    void setBOT(){
        setLayout(null);//清空布局管理器
        start.setFont(new Font("",Font.BOLD,35));//出卷按钮的文字大小
        submit.setFont(new Font("",Font.BOLD,35));//交卷按钮的文字大小
        start.setBounds(900,650,110,60);//出卷按钮的位置和按钮大小
        submit.setBounds(900,720,110,60);//交卷按钮的位置和按钮大小
        start.addActionListener(START);//给出卷按钮置入监视事件
        submit.addActionListener(SUBMIT);//给交卷按钮置入监视事件
        container.add(start);//将出卷按钮加入容器
        container.add(submit);//将交卷按钮加入容器
    }//按钮置入函数
    void FILEBUILD(){
        file = new File("D:/","Report1.dat");//创建新的存储数据的文本
        RandomAccessFile inAndOUT = null;
        double data[]={0,0,0};//定义数组存储数据
        try{
            inAndOUT = new RandomAccessFile(file,"rw");//定义输入输出流
        }catch (Exception e){}
        try{
            for(int i=0;i<=data.length-1;i++){
                inAndOUT.writeDouble(data[i]);//将初始化数据置入文件
            }
            File_Report[2].setText((int)data[0]+"");//给做题量标签赋值
            File_Report[4].setText((int)data[1]+"");//给错题量标签赋值
            File_Report[6].setText(data[2]+"%");//给正确率标签赋值
            inAndOUT.close();//关闭输入输出流
        }catch (Exception e){}
    }//初始配置函数
    void file_Build(){
        File file = new File("D:\\Report1.dat");//查找文件
        if(file.exists()){//文件存在执行该if语句
            //System.out.println("213");
            RandomAccessFile inAndOUT = null;
            double data[]={0,0,0};//定义数组 存储文件内容
            try{
                inAndOUT = new RandomAccessFile(file,"rw");//定义输入输出流 可读可写
            }catch (Exception e){}
            try{
                for(int i=0;i<=data.length-1;i++){
                    inAndOUT.seek(i*8);//double为8字节
                    data[i]=inAndOUT.readDouble();//读取文档内容赋值给data[i]
                }
                File_Report[2].setText((int)data[0]+"");//给做题量的标签文字赋值
                File_Report[4].setText((int)data[1]+"");//给错题量的标签文字赋值
                if(data[0]!=0) {
                    data[2] = ((data[0] - data[1]) / data[0]) * 100;//当文档中的做题量不为0时，直接计算正确率
                    data[2] = (int) (data[2] * 100);
                    data[2] = data[2] / 100;//设置保留小数点后两位
                }
                else{
                    data[2]=0.0;//当做题量为0时直接设置正确率为0防止/0得到NAN值
                }
                File_Report[6].setText(data[2]+"%");//给正确率的标签文字赋值
                inAndOUT.close();//关闭输入输出流
            }catch (Exception e){}
        }
    }//做题量、错题量、正确率的读取置入函数
    void setFile_Report(){
        for(int i=1;i<=6;i++){
            File_Report[i]=new JLabel();
            File_Report[i].setBounds(150+(i-1)*110,700,130,60);
            File_Report[i].setFont(new Font("",Font.BOLD,32));
            container.add(File_Report[i]);
        }
        File_Report[1].setText("做题量:");
        File_Report[3].setText("错题量:");
        File_Report[5].setText("正确率:");
    }
    ActionListener FIRSTBuild = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e3) {
            FILEBUILD();//程序第一次使用时运行的文件
        }
    };//定义创建按钮的监视事件
    void setBOTF(){
        setLayout(null);//清空布局管理器
        FirstB.setFont(new Font("",Font.BOLD,35));//设置创建按钮的文字大小
        FirstB.setBounds(900,790,110,60);//设置创建按钮的位置
        FirstB.addActionListener(FIRSTBuild);//将监视事件FIRSTBuild加入创建按钮
        container.add(FirstB);//置入创建按钮到容器
    }//置入创建按钮
    public MainWindow(String s){
        JLabel title = new JLabel("欢迎使用加减法测试小系统");//设置标签文字
        JLabel First_use = new JLabel("如果您是第一次使用该程序，请先点击创建按钮→");//设置第一次使用提示标签文字
        First_use.setBounds(85,790,1200,60);
        First_use.setFont(new Font("",Font.BOLD,35));
        title.setFont(new Font("",Font.BOLD,45));//这只标签文字大小种类
        title.setForeground(new Color(255,0,0));//标题颜色
        setTitle(s);//窗口标题
        setLocation(375,55);//窗体位置
        setSize( 1200, 950);//窗体的大小
        Container containertitle =getContentPane();//创建一个容器储存标题
        title.setBounds(300,0,3000,150);//设置标题的位置
        containertitle.add(title);//将标签加入容器
        containertitle.add(First_use);//置入第一次使用提示标签
        setFile_Report();//置入做题量、正确率标签
        file_Build();//调用做题量、错题量、正确率的读取置入函数
        makeLAB();//调用被减数、减数、运算符和等号生成并赋值到标签中的函数
        setLAB();//调用把被减数、减数、运算符和等号标签的放入容器的函数
        setBOT();//调用把按钮出卷收卷放入容器的函数
        setBOTF();//调用创建按钮置入函数
        setJText();//调用把答案文本框放入容器的函数
        setVisible(true);//窗体可见
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗体右上角关闭按键执行含义直接关闭程序
    }
}