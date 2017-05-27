/**
 * Created by Inkyu Lee on 2017-05-20.
 */
import java.util.Scanner;

public class main {
    final static int STD_ARR_MAX = 5; //최대 학생 수

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);

        int[] inputTmp = new int[3]; //국영수 점수 입력값 임시 배열
        String nameTmp; //이름 입력값 임시 문자열

        Student std[]; //학생 정보를 담은 객체
        std = new Student[STD_ARR_MAX];

        StudentHandler handler = new StudentHandler();

        for(int i=0;i<STD_ARR_MAX;i++)
        {
            System.out.println("이름을 입력하세요. ");
            nameTmp=sc.nextLine();
            for(int j=0;j<3;j++)
            {
                System.out.println("점수를 입력하세요. ");
                inputTmp[j]=sc.nextInt();
            }
            std[i]=new Student(nameTmp,(byte)inputTmp[0],(byte)inputTmp[1],(byte)inputTmp[2]);
            //Student 객체 생성
            sc.nextLine(); //버퍼 비우기
        }

        handler.StudentSort(std); //학생들 순위 매기기
        handler.StudentPrint(std); //정보 출력하기

    }
}

/**
 * Created by Inkyu Lee on 2017-05-20.
 */
public class Student {
    String name;
    byte Kscore,Mscore,Escore;
    short sum=0,rank;
    float average;

    public Student(String namePm,byte Kpm,byte Mpm,byte Epm)
    {
        this.name=namePm;
        this.Kscore=Kpm;
        this.Mscore=Mpm;
        this.Escore=Epm;
        //입력부분

        sum+=this.Kscore;
        sum+=this.Mscore;
        sum+=this.Escore;

        average=sum/3.0F;
    }

    public void infoPrint()
    {
        System.out.printf("%5s %3d %3d %3d %3d %3.1f %2d\n",this.name,this.Kscore,this.Mscore,this.Escore,this.sum,this.average,this.rank);
    }
}

/**
 * Created by Inkyu Lee on 2017-05-20.
 */

//#define STD_ARR_MAX 5

public class StudentHandler {
    final static int STD_ARR_MAX = 5;

    short Ksum=0,Msum=0,Esum=0;

    public void StudentSort(Student[] stdPm) //각 학생별 순위 구하기
    {
        short cnt;

        for(int i=0;i<STD_ARR_MAX;i++)
        {
            cnt=0;
            for(int j=0;j<STD_ARR_MAX;j++)
            {
                if(stdPm[i].average>=stdPm[j].average)
                {
                    cnt++;
                }
                stdPm[i].rank = (short)(6-cnt);
            }
        }
        return;
    }

    public void StudentPrint(Student[] stdPm) //학생들 정보 출력하기
    {
        System.out.printf("%5s %3s %3s %3s %3s %4s %2s\n","이름","국어","수학","영어","합계","평균","석차");
        for(int i=0;i<STD_ARR_MAX;i++)
        {
            stdPm[i].infoPrint();
        }

        for(int i=0;i<STD_ARR_MAX;i++)
        {
            this.Ksum+=stdPm[i].Kscore;
            this.Msum+=stdPm[i].Mscore;
            this.Esum+=stdPm[i].Escore;
        }
        System.out.printf("%5s %3d %3d %3d\n","합계",this.Ksum,this.Msum,this.Esum);
        System.out.printf("%5s %3f %3f %3f\n","평균",this.Ksum/5.0F,this.Msum/5.0F,this.Esum/5.0f);
    }

}
