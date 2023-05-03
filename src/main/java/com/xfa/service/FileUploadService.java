package com.xfa.service;

import com.opencsv.CSVReader;
import com.xfa.handler.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 0xfa
 */
@Service
@Log4j2
public class FileUploadService {
    private static final String CSV_NAME = ".csv";

    public void getFilesList(MultipartFile[] files) {
        Arrays.stream(files).forEach(this::csvAnalysis);
    }

    public void csvAnalysis(MultipartFile csv) {
        if (csv.isEmpty()) {
            throw new BusinessException("文件为空");
        }
        if (!csv.getOriginalFilename().endsWith(CSV_NAME)) {
            throw new BusinessException("文件不为csv");
        }
        StopWatch stopWatch = new StopWatch(csv.getOriginalFilename()+"任务处理");
        stopWatch.start();
        // 解析CSV文件内容
        try (CSVReader reader = new CSVReader(new InputStreamReader(csv.getInputStream()))) {
            List list = new LinkedList<>();

            String[] line;
            while ((line = reader.readNext()) != null) {
                // 处理CSV文件中的每一行数据
                // TODO: 处理CSV文件中的数据
                System.out.println(Arrays.toString(line));
            }
        } catch (Exception e) {
            log.error(e);
            throw new BusinessException("任务失败");
        }
        stopWatch.stop();
        log.info(stopWatch.getMessage());
    }
}
