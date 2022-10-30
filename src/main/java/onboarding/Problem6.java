package onboarding;

import java.util.*;

public class Problem6 {
    private static final int FORM_EMAIL = 0;
    private static final int FORM_NICKNAME = 1;
    private static List<String> answer = new ArrayList<>();
    private static List<Set<String>> partsOfNicknameList = new ArrayList<>();
    private static Map<String, Integer> nicknameAndCountMap = new HashMap<>();

    public static List<String> solution(List<List<String>> forms) {
        setupData(forms);
        getAnswer(forms);
        Collections.sort(answer);
        return answer;
    }

    public static void getAnswer(List<List<String>> forms) {
        for (int i = 0; i < partsOfNicknameList.size(); i++) {
            if (partsOfNicknameList.get(i).stream().anyMatch(part -> nicknameAndCountMap.get(part) > 1)) {
                answer.add(forms.get(i).get(FORM_EMAIL));
            }
        }
    }

    public static void setupData(List<List<String>> forms) {
        forms.stream()
                .map(form -> nicknameDecomposition(form.get(FORM_NICKNAME)))
                .forEach(parts -> {
                    partsOfNicknameList.add(parts);
                    parts.forEach(part -> nicknameAndCountMap.put(part, nicknameAndCountMap.getOrDefault(part, 0) + 1));
                });
    }

    public static Set<String> nicknameDecomposition(String nickname) {
        Set<String> separatedNickname = new HashSet<>();
        for (int i = 0; i < nickname.length() - 1; i++) {
            separatedNickname.add(nickname.substring(i, i + 2));
        }
        return separatedNickname;
    }
}
