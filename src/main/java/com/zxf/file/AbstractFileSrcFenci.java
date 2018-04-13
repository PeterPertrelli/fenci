package com.zxf.file;

import com.hankcs.hanlp.dictionary.other.PartOfSpeechTagDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.zxf.process.SimultaneousProcess;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxiangfei
 * @Description:
 * @date 2018/4/10
 */
public abstract class AbstractFileSrcFenci implements SimultaneousProcess {
    @Override
    public String prepareResultFilePath() {
        return "F:/fenci/file/test-out.txt";
    }

    @Override
    public List<String> prepareSrcFileListPath() {
        List<String> fileList = new ArrayList<String>();
        fileList.add("F:/fenci/file/test.txt");
        return fileList;
    }

    @Override
    public boolean needPrint(String line) {
        return true;
    }

    @Override
    public void processAndPrint(String line, String fileName, FileWriter fw) throws IOException {

        List<Term> termList = getTerms(line);

        StringBuilder sb  = new StringBuilder();
        for(Term term : termList){
            sb.append(term.word + "  ");
        }
        fw.write(sb.toString() + "\r\n");
    }

    public abstract List<Term> getTerms(String line);

}
