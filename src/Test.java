import java.util.Arrays;
public class Test {
    //found表示是否找到密码，用于提前终止递归
    public static boolean found;
    //count用于记录递归调用次数
    public static int count = 0;

    public static void main(String[] args){
        //练习-随机字符串长度
        System.out.println(randomStr(5));
        System.out.println();

        //练习-字符串数组排序
        String[] strSet = new String[8];
        for(int i = 0;i < 8;i++){
            strSet[i] = randomStr(5);
        }
        System.out.println("array before sorting:");
        printStrSet(strSet);
        System.out.println("array after sorting:");
        stringSort(strSet);
        printStrSet(strSet);
        System.out.println();

        //练习-穷举法破解密码
        String randomStr = randomStr(3);
        System.out.println("the key to crack is:" + randomStr);
        //for循环
        System.out.println("method 1:" );
        crackKey(randomStr);
        //递归
        System.out.println("method 2:" );
        crackKey(randomStr,0,new char[3]);
    }

    //在范围[a,b)内生成随机整数
    public static int randomInt(int a,int b){
        return (int)(a + (b - a) * Math.random());
    }

    //生成长度为length的随机字符串
    public static String randomStr(int length){
        char[] randChar = new char[length];
        int i = 0;

        while(i < length){
            int c = randomInt('0','z' + 1);
            //将非数字和字母的字符剔除
            if (c <= '9' || (c >= 'A' && c <= 'Z') || c >= 'a'){
                randChar[i] = (char)c;
                i++;
            }
        }
        return new String(randChar);
    }

    //对字符串按首字母进行排序
    public static String[] stringSort(String[] stringToSort){
        //获取字符串的首字母并全部转换为小写
        char[] headLetter = new char[stringToSort.length];
        for (int i = 0;i < stringToSort.length;i++){
            headLetter[i] = Character.toLowerCase(stringToSort[i].toCharArray()[0]);
        }
        //测试
        //System.out.println(new String(headLetter));

        //按首字母对字符串进行排序，选择排序法
        for (int i = 0;i < headLetter.length - 1;i++){
            int minLetterIndex = i;
            for (int j = i + 1;j < headLetter.length;j++)
                if (headLetter[minLetterIndex] > headLetter[j])
                    minLetterIndex = j;
            if (minLetterIndex != i){
                char tempChar = headLetter[i];
                String tempStr = stringToSort[i];

                headLetter[i] = headLetter[minLetterIndex];
                stringToSort[i] = stringToSort[minLetterIndex];

                headLetter[minLetterIndex] = tempChar;
                stringToSort[minLetterIndex] = tempStr;
            }
        }
        //测试
        //System.out.println(new String(headLetter));
        return stringToSort;
    }

    //用for循环破解密码，只能破解三位数的密码
    public static void crackKey(String key){
        //定义仅由数字和字母组成的字符数组，用于生成破解码
        char[] numAndLetters = new char[62];
        int index = 0;
        for (int i = '0';i <= 'z';i++){
            if (Character.isLetterOrDigit(i)){
                numAndLetters[index] = (char)i;
                index++;
            }
        }
        //利用三层嵌套循环遍历所有可能的字符组合，并找到与密码吻合的组合
        int testTimes = 0;
        char[] testKey = new char[3];
        char[] realKey = key.toCharArray();
        for (int i = 0;i < numAndLetters.length;i++){
            for (int j = 0;j < numAndLetters.length;j++){
                for(int k = 0;k < numAndLetters.length;k++){
                    testTimes++;
                    testKey[0] = numAndLetters[i];
                    testKey[1] = numAndLetters[j];
                    testKey[2] = numAndLetters[k];
                    if (Arrays.equals(testKey, realKey)){
                        System.out.println("the key is " + new String(testKey));
                        System.out.println("test times:" + testTimes);
                        return;
                    }
                }
            }
        }
    }

    //用递归破解密码
    public static void crackKey(String key, int index, char[] guess) {
        //若找到密码，则直接退出递归
        if (found)
            return;
        count++;
        //利用for循环剔除非数字和字母字符
        for (int i = '0'; i <= 'z'; i++) {
            if (!Character.isLetterOrDigit(i))
                continue;
            guess[index] = (char) i;
            //若未遍历至密码最后一位，则递归调用破解方法
            if (index != guess.length - 1) {
                crackKey(key, index + 1, guess);
            }
            //找到密码后将静态变量found赋值为true，结束递归调用
            else if (Arrays.equals(guess, key.toCharArray())) {
                found = true;
                System.out.println("the key is " + new String(guess));
                System.out.println("test times:" + count);
                return;
            }
        }
    }

    //打印字符串数组
    public static void printStrSet(String[] stringToPrint){
        for (String str : stringToPrint)
            System.out.println(str);
    }
}