package baseball;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {

    // 컴퓨터가 서로 다른 랜덤의 3자리 수를 정하는 함수
    private static void rand_num_decision(List<Integer> randnum){
        while (randnum.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!randnum.contains(randomNumber)) {
                randnum.add(randomNumber);
            }
        }
    }

    private static String user_num_input(){
        return Console.readLine();
    }

    private static int game_reset(){
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        return Integer.parseInt(Console.readLine());
    }

    private static int[] check(String user, List<Integer> com){
        int i, ball = 0, strike = 0;
        for (i = 0; i < 3; i++){
            if (Character.getNumericValue(user.charAt(i)) == com.get(i)){
                strike++;
                continue;
            }
            if (com.contains(Character.getNumericValue(user.charAt(i)))){
                ball++;
            }
        }
        return new int[] {ball, strike};
    }

    private static boolean result(int[] bs){
        String output = "";

        if (bs[0] != 0){
            output += Integer.toString(bs[0]) + "볼 ";
        }

        if (bs[1] != 0){
            output += Integer.toString(bs[1]) + "스트라이크";
        }

        if (output == ""){
            output = "낫싱";
        }

        System.out.println(output);

        if (bs[1] == 3){
            return false;
        }
        else{
            return true;
        }
    }

    private static int compare(List<Integer> com_num){
        String user_num;
        Boolean flag;
        do{
            user_num = user_num_input();
            flag = result(check(user_num, com_num));
        } while (flag);
        return game_reset();
    }

    private static void game(){
        int game_status;
        List<Integer> computer = new ArrayList<>();

        do {
            rand_num_decision(computer);
            game_status = compare(computer);
        } while (game_status == 1);
    }

    public static void main(String[] args) {

        System.out.println("숫자 야구 게임을 시작합니다.");
        game();

    }
}
