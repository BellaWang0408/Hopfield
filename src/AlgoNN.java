import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class AlgoNN 
{
	public float w[];
	public float warr[][];
	public float testa[][];
	public float s[];
    public float weight;
	public float l;
    public float h;
	public int random;
    public int rate[];
    private String x;
	private String y;
	public float rantest[][];
	public ArrayList<ArrayList<Float>> train;
	public ArrayList<ArrayList<Float>> test;
    
	public void run(String arga,String argb) throws IOException
	{
		x = arga;
		y = argb;
		train=new ArrayList<ArrayList<Float>>();
		test=new ArrayList<ArrayList<Float>>();
		read(train, x);
		read(test, y);
		System.out.println(x);
		System.out.println(y);
		h=train.size();
		weight=train.get(0).size();
		w=new float[(int) (weight*weight)];
		warr=new float[(int) weight][(int) weight];
		testa=new float[(int)h][(int) weight];
		rate=new int[(int) h];
		int count;
		rantest=new float[(int)h][(int) weight];
		
		for(int i=0;i<h;i++)
		{
			for(int j=0;j<weight;j++)
			{
				Random ran = new Random();
				count=ran.nextInt(random)+1;
				if((count==random) && (random!=1))
					rantest[i][j]=(-1)*test.get(i).get(j);
				else
					rantest[i][j]=test.get(i).get(j);
					//System.out.println(rantest);
					System.out.println(j+","+i+","+train.size()+","+train.get(i).size()+","+h);
			}
		}
		s=new float[(int) weight];
		for(int i=0;i<weight*weight-1;i++)
		{
			w[i]=0;
		}
		
		for(int i=0;i<h;i++)
		{
			int temp=0;
			for(int j=0;j<weight;j++)
			{
				for(int k=0;k<weight;k++)
				{
					w[temp]=w[temp]+train.get(i).get(j)*train.get(i).get(k);
					temp++;	
				}
			}
		}
		
		for(int i=0;i<w.length;i++)
		{
			w[i]=w[i]/weight;
		}

		int temp=0;
		for(int i=0;i<weight;i++)
		{
			for(int j=0;j<weight;j++)
			{
				if(i==j)
					w[temp]=w[temp]-(h/weight);
				temp++;
			}
		}
		int count1=0;
		for(int i=0;i<weight;i++)
		{
			for(int j=0;j<weight;j++)
			{
				warr[i][j]=w[count1];
				count1++;
			}
		}
		for(int i=0;i<weight;i++)
		{
			s[i]=0;
		}
		
		float sgn=0;
		for(int i=0;i<500;i++)
		{
			for(int j=0;j<h;j++)
			{
				for(int k=0;k<weight;k++)
				{
					for(int l=0;l<weight;l++)
					{
						sgn=sgn+warr[k][l]*rantest[j][l];
					}
					sgn=sgn-s[k];
					if(sgn>0) testa[j][k]=1;
					else if(sgn==0) testa[j][k]=rantest[j][k];
					else if(sgn<0)  testa[j][k]=-1;
					sgn=0;
				}
			}
		}
		
		for(int i=0;i<h;i++)
		{
			for(int j=0;j<weight;j++)
			{
				if(testa[i][j]==train.get(i).get(j))
					rate[i]=rate[i]+1;
			}
		}
		
		for(int i=0;i<h;i++)
		{
			rate[i]=(int) ((rate[i]*100)/weight);
			//System.out.println("testrate["+i+"]="+(rate[i]*100)/weight+"%");
		}
	}
	public void read(ArrayList<ArrayList<Float>>input, String x) throws IOException
	{
		FileReader fr = new FileReader(x);
		BufferedReader br = new BufferedReader(fr);
		input.add(new ArrayList<Float>()) ;
		for(String txt = br.readLine() ; txt != null ;txt = br.readLine())
		{
			l= txt.length();
			if(txt.length()==1)
			{
				input.add(new ArrayList<Float>()) ;
			}
			System.out.println(txt);
			for ( int i = 0 ; i < txt.length() ; i++ ) 
			{
				if ( txt.charAt(i) == '1' )
					input.get(input.size()-1).add((float) 1) ;
				else 
					input.get(input.size()-1).add((float) -1) ;
			}
		} 
	}
}
