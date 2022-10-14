package assignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Represents the class used to read and write objects to the text file
 * @author Admin
 *
 */
public class ObjectCreator {
/**
 * Writes an arrayList to the text file 
 * @param list
 * @param file
 */
	public void writeArray(ArrayList<?> list ,String file) {
		try {
			FileOutputStream fs = new FileOutputStream(file);
			ObjectOutputStream os=new ObjectOutputStream(fs);
			os.writeObject(list);
			os.close();
			fs.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Creates an arrayList object based on the contents in the text file
	 * @param list
	 * @param file
	 * @return
	 */
	public ArrayList<?>  readArray (ArrayList<?> list ,String file) {
		try {
			FileInputStream fi=new FileInputStream(file);
			ObjectInputStream os=new ObjectInputStream(fi);
			list = (ArrayList<? extends MenuItem> ) os.readObject();
			os.close();
			fi.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			 //occurs when initial file is empty, so return set as it is
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * Creates an hashMap object based on the contents in the text file
	 * @param hm
	 * @param file
	 * @return
	 */
	public Map<?, ?> readHashMap(Map<?, ?> hm , String file) {
		try {
			FileInputStream fi=new FileInputStream(file);
			ObjectInputStream os=new ObjectInputStream(fi);
			hm =  (Map<?, ?> )os.readObject(); 
			os.close();
			fi.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			 //occurs when initial file is empty, so return set as it is
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hm;
	}
	
	/**
	 * Writes a hashmap object to the text file 
	 * @param hm
	 * @param file
	 */
	public void writeHashMap(Map<?, ?> hm , String file) {
		try {
			FileOutputStream fs = new FileOutputStream(file);
			ObjectOutputStream os=new ObjectOutputStream(fs);
			os.writeObject(hm);
			os.close();
			fs.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
