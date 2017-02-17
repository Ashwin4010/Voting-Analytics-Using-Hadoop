
import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
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
public class ElectionMapperB extends MapReduceBase implements Mapper<LongWritable,Text,Text,DoubleWritable>{

    @Override
    public void map(LongWritable key, Text val, OutputCollector<Text, DoubleWritable> outputCollector, Reporter reporter) throws IOException {
        String info=val.toString();
        String electionData[]=info.split(",");
        if(electionData.length>4&&Character.isDigit(electionData[3].charAt(0)))   {
            int total=Integer.parseInt(electionData[3]);
            String distName=electionData[2];
            outputCollector.collect(new Text(distName), new DoubleWritable(total));
        }
    }
}
