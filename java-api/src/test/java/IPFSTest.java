import com.cyber.blockchain.ipfs.IPFSManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * help:
 * 1.上传文件       String       AddFileIPFS(byte[] content)
 * 2.批量文件上传    List<String> AddFileBatchIPFS(List<byte[]> contents)
 * 3.下载文件       byte[]       GetFileIPFS(String hash)
 * 4.批量下载文件    List<byte[]> GetFileBatchIPFS(List<String> hashs)
 * 5.删除集群文件    String       DeleteFileIPFS(String hash)
 * 6.批量删除集群文件 List<String> DeleteBatchFileIPFS(List<String> hashs)
 * 7.查询集群文件    List<String> LsFileIPFS()
 */

public class IPFSTest {

    public static void main(String args[]) {
        String file1 = "/home/littleredhat/Music/me.jpg";
        byte[] byte1 = file2byte(file1);

        String file2 = "/home/littleredhat/Music/海棠.mp4";
        byte[] byte2 = file2byte(file2);

        List<String> file3 = new ArrayList<String>();
        file3.add(file1);
        file3.add(file2);

        List<byte[]> byte3 = new ArrayList<byte[]>();
        byte3.add(byte1);
        byte3.add(byte2);

        IPFSManager ic = new IPFSManager();

        System.out.println("========== 上传文件 ==========");
        List<String> addList = ic.AddFileBatchIPFS(byte3);
        System.out.println(addList);

//        System.out.println("========== 下载文件 ==========");
//        List<byte[]> getList = ic.GetFileBatchIPFS(addList);
//        System.out.println(getList);

//        System.out.println("========== 删除文件 ==========");
//        List<String> deleteList = ic.DeleteBatchFileIPFS(addList);
//        System.out.println(deleteList);

//        System.out.println("========== 查询文件 ==========");
//        List<String> lsList = ic.LsFileIPFS();
//        System.out.println(lsList);
    }

    /**
     * 文件 => byte[]
     *
     * @param path
     * @return
     */
    public static byte[] file2byte(String path) {
        byte[] ret = null;
        try {
            File file = new File(path);
            if (file == null) {
                return null;
            }
            FileInputStream in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] b = new byte[1024 * 8];
            int n = 0;
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
            out.close();
            in.close();
            ret = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * byte[] => 文件
     *
     * @param path
     * @param data
     */
    public static void byte2file(String path, byte[] data) {
        try {
            FileOutputStream fs = new FileOutputStream(new File(path));
            fs.write(data);
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
