
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * @author rajesh
 */
public class ElectionMapperA extends MapReduceBase implements Mapper<LongWritable,Text,Text,IntWritable>{

    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        String info=value.toString();
        String electionData[]=info.split(",");
        if(electionData.length>4&&Character.isDigit(electionData[3].charAt(0)))   {
            int total=Integer.parseInt(electionData[3]);
            String distName=electionData[2];
            output.collect(new Text(distName), new IntWritable(total));
        }
    }
}
