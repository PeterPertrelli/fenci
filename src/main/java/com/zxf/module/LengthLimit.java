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
 * @date 2018/4/11
 */
public class LengthLimit extends AbstractFileSrcFenci {

    private static final int LIMIT = 120;

    public static void main(String[] args) {
        LengthLimit extractor = new LengthLimit();
        ReadSimultaneousProcessMachine.exec(extractor);
    }

    @Override
    public List<Term> getTerms(String line) {
        List<Term> result;
        result = HanLP.segment(line);

        return result;
    }

    @Override
    public void processAndPrint(String line, String fileName, FileWriter fw) throws IOException {

        List<Term> termList = getTerms(line);

        StringBuilder sb  = new StringBuilder();
        for(int i = 0; i < termList.size(); i++){
            Term term = termList.get(i);

            if(StringUtils.getChineseLength(sb.toString())+StringUtils.getChineseLength(term.word)>LIMIT){
                printFile(fw, sb);
            }
            sb.append(term.word + "  ");
            if(i == (termList.size()-1)){
                printFile(fw,sb);
            }
        }
        fw.write(sb.toString() + "\r\n");
    }

    private void printFile(FileWriter fw, StringBuilder sb) throws IOException {
        fw.write(sb.toString() + "\r\n");
        sb.delete(0,sb.length());
    }

}
