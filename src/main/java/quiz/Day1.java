package quiz;

import java.io.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Day1 {
    /**
     * 명령프롬포트
     * 입력: 첫째 줄에 파일 이름의 개수 N이 주어진다.
     *      둘째 줄부터 N개의 줄에는 파일 이름이 주어진다.
     *      N은 50보다 작거나 같은 자연수이고 파일 이름의 길이는 모두 같고 길이는 최대 50이다.
     *      파일이름은 알파벳 소문자와 '.' 로만 이루어져 있다.
     * 출력: 첫째 줄에 패턴 출력
     *      패턴에는 알파벳과 "." 그리고 "?"만 넣을 수 있다. 가능하면 ?을 적게 써야 한다
     */
    public void prompt() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder str = new StringBuilder();

        String firstLine = br.readLine();
        try {
            Integer.parseInt(firstLine);
        } catch (NumberFormatException e) {
            throw new RuntimeException("첫번째 행은 파일 이름의 개수로, 50 이하의 자연수만 가능");
        }
        int counts = Integer.parseInt(firstLine);
        if (counts > 50) {
            throw new RuntimeException("첫번째 행은 파일 이름의 개수로, 50 이하의 자연수만 가능");
        }
        int fileNameLength = 0;
        Set<String> fileSet = new HashSet<>();

        for (int i = 0; i < counts; i++) {
            String fileName = br.readLine();
            fileSet.add(fileName);
            fileNameLength = fileName.length();
        }

        long fileNameLengthCnt = fileSet.stream().map(String::length).distinct().count();
        if (fileNameLengthCnt > 1) {
            throw new RuntimeException("파일명의 글자수는 동일해야합니다.");
        }

        for (int i = 0; i < fileNameLength; i++) {
            int finalI = i;
            long count = fileSet.stream().map(fileName -> fileName.charAt(finalI)).distinct().count();
            Optional<Character> characterOptional = fileSet.stream().map(fileName -> fileName.charAt(finalI)).distinct().findFirst();
            boolean present = characterOptional.isPresent();
            String distinct = "";
            if (present) {
                distinct = characterOptional.get().toString();
            }
            str.append(count == 1 ? distinct : "?");
        }

        bw.write(str.toString());
        bw.flush();
        bw.close();
    }
}
