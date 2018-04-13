package com.zxf.module;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.zxf.file.AbstractFileSrcFenci;
import com.zxf.machine.ReadSimultaneousProcessMachine;
import com.zxf.util.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author zhuxiangfei
 * @Description:
 * @date 2018/4/13
 */
public class WidthTrainning extends AbstractFileSrcFenci {

    private static final int SEGMENT = 10;

    public static void main(String[] args) {
        WidthTrainning extractor = new WidthTrainning();
        ReadSimultaneousProcessMachine.exec(extractor);
    }


    @Override
    public String prepareResultFilePath() {
        return "F:/fenci/file/test-out-width-training.txt";
    }

    @Override
    public void processAndPrint(String line, String fileName, FileWriter fw) throws IOException {
        StringBuilder sb  = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            sb.append(line.substring(i, i + 1));
            if((i+1)%SEGMENT == 0){
                sb.append("\r\n");
            }
        }
        fw.write(sb.toString() + "\r\n");
    }

    @Override
    public List<Term> getTerms(String line) {
        return null;
    }

    private void printFile(FileWriter fw, StringBuilder sb) throws IOException {
        fw.write(sb.toString() + "\r\n");
        sb.delete(0,sb.length());
    }
}
