
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


/**
 *
 * @author rajesh
 */
public class ElectionDriver extends Configured implements Tool{
    
    @Override
    public int run(String[] args) throws Exception {
        if(args.length<3)   {
            return -1;
        }
        JobConf config1=new JobConf(ElectionDriver.class);
        Path input1=new Path(args[0]);
        Path output1=new Path(args[2]+"/A");
        Path input2=new Path(args[1]);
        Path output2=new Path(args[2]+"/B");
        
        FileSystem file=FileSystem.get(config1);
        if(file.exists(output1))   {
        	file.delete(output1,true);
        }
        if(file.exists(output2))   {
        	file.delete(output2,true);
        }
        FileInputFormat.setInputPaths(config1,input1,input2);
        FileOutputFormat.setOutputPath(config1,output1);
        config1.setMapperClass(ElectionMapperA.class);
        config1.setReducerClass(ElectionReducerA.class);
        config1.setMapOutputKeyClass(Text.class);
        config1.setMapOutputValueClass(IntWritable.class);
        config1.setOutputKeyClass(Text.class);
        config1.setOutputValueClass(LongWritable.class);
        JobConf config2=new JobConf(ElectionDriver.class);
        FileInputFormat.setInputPaths(config2,input1,input2);
        FileOutputFormat.setOutputPath(config2,output2);
        config2.setMapperClass(ElectionMapperB.class);
        config2.setCombinerClass(ElectionCombiner.class);
        config2.setReducerClass(ElectionReducerB.class);
        config2.setMapOutputKeyClass(Text.class);
        config2.setMapOutputValueClass(DoubleWritable.class);
        config2.setOutputKeyClass(Text.class);
        config2.setOutputValueClass(DoubleWritable.class);
        config2.setNumReduceTasks(1);
        JobClient.runJob(config1);
        JobClient.runJob(config2);
        return 0;
    }
    public static void main(String args[]) throws Exception {
        int exit=ToolRunner.run(new ElectionDriver(), args);
        System.exit(exit);
    }
    
}
