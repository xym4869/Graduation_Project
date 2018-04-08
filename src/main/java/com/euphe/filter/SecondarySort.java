package com.euphe.filter;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Partitioner;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class SecondarySort {
	
	public static class MACTimePair implements WritableComparable<MACTimePair> {
		private String MAC;
		private String time;
		
		public String getMAC() {
			return MAC;
		}

		public String getTime() {
			return time;
		}
		
		public void set(String mac,String time){
			this.MAC = mac;
			this.time = time;
		}

		public void write(DataOutput out) throws IOException {
			out.writeUTF(MAC);
			out.writeUTF(time);
			
		}

		public void readFields(DataInput in) throws IOException {
			this.MAC = in.readUTF();
			this.time = in.readUTF();
			
		}
		
		@Override
		public int hashCode(){
			return this.MAC.hashCode()*127 + this.time.hashCode();
		}
		
		@Override
		public boolean equals(Object right){
			if(right instanceof MACTimePair){
				MACTimePair r = (MACTimePair) right;
				return r.MAC == MAC && r.time == time;
			}else{
				return false;
			}
		}

		public int compareTo(MACTimePair o) {
			if(!MAC.equals(o.MAC)){
				return MAC.compareTo(o.MAC);
			}
			else if(!time.equals(o.time)){
				return time.compareTo(o.time);
			}
			else {
				return 0;
			}
		}
		
		public String toString(){
			return MAC + "\t" + time;
		}

	}

	public static class FirstPartitioner  extends Partitioner<MACTimePair, Text>{

		@Override
		public int getPartition(MACTimePair key, Text value, int numPartitions) {
			return Math.abs(key.getMAC().hashCode() * 127) % numPartitions;
		}

	}

	public static class KeyComparator extends WritableComparator{
		protected KeyComparator(){
			super(MACTimePair.class,true);
		}
		
		@Override
		public int compare(WritableComparable w1, WritableComparable w2){
			MACTimePair mtp1 = (MACTimePair) w1;
			MACTimePair mtp2 = (MACTimePair) w2;
			
			int cmp = mtp1.compareTo(mtp2);
			return cmp;
		}

	}

	public static class GroupComparator extends WritableComparator{
		protected GroupComparator(){
			super(MACTimePair.class,true);
		}
		
		@Override
		public int compare(WritableComparable w1, WritableComparable w2){
			MACTimePair mtp1 = (MACTimePair) w1;
			MACTimePair mtp2 = (MACTimePair) w2;
			
			int cmp = mtp1.getMAC().compareTo(mtp2.getMAC());
			return cmp;
		}

	}
	
}
