package hb.brain;

public class ServiceHandle {

    private static StringBuffer sb = new StringBuffer("Never Disp!");

    public static void clearDataBase() {
        DataBaseRepository.clearSelf();
    }

    public static void getDiceArrAndCalc(int[] diceArr,boolean isCalc) {
        boolean isTriple = false;
        if(diceArr[0]==diceArr[1] && diceArr[1]==diceArr[2]){
            isTriple = true;
        }
        sb.delete(0, sb.length());
        int sums = 0;
        sortArray(diceArr, 0, diceArr.length - 1);
        String diceStrHolder = "";
        for (int i = 0; i < diceArr.length; i++) {
            sums += diceArr[i];
            sb.append(diceArr[i]);
        }
        diceStrHolder = sb.toString();
        DataBaseRepository.strAuto = diceStrHolder;

        //judge six dice
        DataBaseRepository.intSingle1ns++;
        DataBaseRepository.intSingle2ns++;
        DataBaseRepository.intSingle3ns++;
        DataBaseRepository.intSingle4ns++;
        DataBaseRepository.intSingle5ns++;
        DataBaseRepository.intSingle6ns++;

        if(isCalc){
            if(sums>=11 && sums<18 && (!isTriple)){
                DataBaseRepository.money+=DataBaseRepository.amountBig;
                DataBaseRepository.money-=DataBaseRepository.amountSml;
            }
            if(sums>=4 && sums<=10 && (!isTriple)){
                DataBaseRepository.money+=DataBaseRepository.amountSml;
                DataBaseRepository.money-=DataBaseRepository.amountBig;
            }
            if(sums%2==0 && (!isTriple)){
                DataBaseRepository.money+=DataBaseRepository.amountEven;
                DataBaseRepository.money-=DataBaseRepository.amountOdd;
            }
            if(sums%2!=0 && (!isTriple) ){
                DataBaseRepository.money-=DataBaseRepository.amountEven;
                DataBaseRepository.money+=DataBaseRepository.amountOdd;
            }

            DataBaseRepository.amountBig =0;
            DataBaseRepository.amountSml =0;
            DataBaseRepository.amountEven =0;
            DataBaseRepository.amountOdd =0;
        }
        for (int i = 0; i < diceArr.length; i++) {
            switch (diceArr[i]) {
                case 1:
                    DataBaseRepository.intSingle1ns = 0;
                    break;
                case 2:
                    DataBaseRepository.intSingle2ns = 0;
                    break;
                case 3:
                    DataBaseRepository.intSingle3ns = 0;
                    break;
                case 4:
                    DataBaseRepository.intSingle4ns = 0;
                    break;
                case 5:
                    DataBaseRepository.intSingle5ns = 0;
                    break;
                case 6:
                    DataBaseRepository.intSingle6ns = 0;
                    break;
                default:
                    break;
            }
        }
        //end judge six dice
        DataBaseRepository.intF3456ns++;
        DataBaseRepository.intF2356ns++;
        DataBaseRepository.intF2345ns++;
        DataBaseRepository.intF1234ns++;
        //the four

        //end the four

        switch (sums) {
            case 18:                                                    //666
                //666
                DataBaseRepository.intBig++;
                DataBaseRepository.intBigDiff = 0;
                DataBaseRepository.intSmallDiff = 0;
                //
                DataBaseRepository.anyDouble++;
                DataBaseRepository.anyDoubleDiff++;
                DataBaseRepository.anyThreeDiff = 0;
                //
                DataBaseRepository.intEven++;
                DataBaseRepository.intOddDiff = 0;
                DataBaseRepository.intEvenDiff = 0;
                //
                DataBaseRepository.anyTriple++;
                DataBaseRepository.intTriple6++;
                DataBaseRepository.intSingle6 += 3;
                break;
            case 17:                                                    //566
                //
                DataBaseRepository.intBig++;
                DataBaseRepository.intBigDiff++;
                DataBaseRepository.intSmallDiff = 0;
                //
                DataBaseRepository.intOdd++;
                DataBaseRepository.intOddDiff++;
                DataBaseRepository.intEvenDiff = 0;
                //
                DataBaseRepository.anyDouble++;
                DataBaseRepository.anyDoubleDiff++;
                DataBaseRepository.anyThreeDiff = 0;
                //
                DataBaseRepository.intSingle6 += 2;
                DataBaseRepository.intSingle5++;
                //
                DataBaseRepository.sum17++;
                //
                DataBaseRepository.com56++;
                break;
            case 16:
                DataBaseRepository.intBig++;
                DataBaseRepository.intBigDiff++;
                DataBaseRepository.intSmallDiff = 0;
                //
                DataBaseRepository.intEven++;
                DataBaseRepository.intEvenDiff++;
                DataBaseRepository.intOddDiff = 0;
                //
                DataBaseRepository.anyDouble++;
                DataBaseRepository.anyDoubleDiff++;
                DataBaseRepository.anyThreeDiff = 0;
                //
                DataBaseRepository.sum17++;
                if (diceStrHolder.startsWith("4")) {                    //466
                    DataBaseRepository.com46++;
                    DataBaseRepository.intSingle6 += 2;
                    DataBaseRepository.intSingle4++;
                } else {                                                //556
                    DataBaseRepository.com56++;
                    DataBaseRepository.intSingle5 += 2;
                    DataBaseRepository.intSingle6++;
                }
                break;
            case 15:
                if (diceStrHolder.startsWith("5")) {                    //555
                    //555
                    DataBaseRepository.intBig++;
                    DataBaseRepository.intBigDiff = 0;
                    DataBaseRepository.intSmallDiff = 0;
                    //
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                    //
                    DataBaseRepository.intOdd++;
                    DataBaseRepository.intOddDiff = 0;
                    DataBaseRepository.intEvenDiff = 0;

                    DataBaseRepository.intSingle5 += 3;
                    DataBaseRepository.intTriple5++;
                    DataBaseRepository.anyTriple++;
                } else {
                    DataBaseRepository.intBig++;
                    DataBaseRepository.intBigDiff++;
                    DataBaseRepository.intSmallDiff = 0;
                    //
                    DataBaseRepository.intOdd++;
                    DataBaseRepository.intOddDiff++;
                    DataBaseRepository.intEvenDiff = 0;

                    if (diceStrHolder.startsWith("3")) {                //366
                        DataBaseRepository.anyDouble++;
                        DataBaseRepository.anyDoubleDiff++;
                        DataBaseRepository.anyThreeDiff = 0;
                        //
                        DataBaseRepository.com36++;
                        DataBaseRepository.intSingle6 += 2;
                        DataBaseRepository.intSingle3++;
                    } else {                                            //456
                        DataBaseRepository.com56++;
                        DataBaseRepository.com46++;
                        DataBaseRepository.com45++;
                        DataBaseRepository.intSingle4++;
                        DataBaseRepository.intSingle5++;
                        DataBaseRepository.intSingle6++;
                        DataBaseRepository.intF3456++;
                        DataBaseRepository.intF3456ns = 0;
                        //
                        DataBaseRepository.anyThree++;
                        DataBaseRepository.anyThreeDiff++;
                        DataBaseRepository.anyDoubleDiff = 0;
                    }
                }
                DataBaseRepository.sum15++;
                break;
            case 14:
                DataBaseRepository.intBig++;
                DataBaseRepository.intBigDiff++;
                DataBaseRepository.intSmallDiff = 0;
                //
                DataBaseRepository.intEven++;
                DataBaseRepository.intEvenDiff++;
                DataBaseRepository.intOddDiff = 0;
                //
                DataBaseRepository.sum14++;
                if (diceStrHolder.startsWith("2")) {                    //266
                    DataBaseRepository.com26++;
                    DataBaseRepository.intSingle6 += 2;
                    DataBaseRepository.intSingle2++;
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                } else if (diceStrHolder.startsWith("3")) {             //356
                    DataBaseRepository.com56++;
                    DataBaseRepository.com36++;
                    DataBaseRepository.com35++;
                    DataBaseRepository.intSingle3++;
                    DataBaseRepository.intSingle5++;
                    DataBaseRepository.intSingle6++;
                    DataBaseRepository.intF3456++;
                    DataBaseRepository.intF3456ns = 0;
                    DataBaseRepository.intF2356++;
                    DataBaseRepository.intF2356ns = 0;
                    //
                    DataBaseRepository.anyThree++;
                    DataBaseRepository.anyThreeDiff++;
                    DataBaseRepository.anyDoubleDiff = 0;
                } else if (diceStrHolder.startsWith("45")) {            //455
                    DataBaseRepository.com45++;
                    DataBaseRepository.intSingle5 += 2;
                    DataBaseRepository.intSingle4++;
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                } else {                                                //446
                    DataBaseRepository.com46++;
                    DataBaseRepository.intSingle4 += 2;
                    DataBaseRepository.intSingle6++;
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                }
                break;
            case 13:
                DataBaseRepository.intBig++;
                DataBaseRepository.intBigDiff++;
                DataBaseRepository.intSmallDiff = 0;
                //
                DataBaseRepository.intOdd++;
                DataBaseRepository.intOddDiff++;
                DataBaseRepository.intEvenDiff = 0;
                //
                DataBaseRepository.sum13++;
                if (diceStrHolder.startsWith("1")) {                    //166
                    DataBaseRepository.com16++;
                    DataBaseRepository.intSingle6 += 2;
                    DataBaseRepository.intSingle1++;
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                } else if (diceStrHolder.startsWith("35")) {            //355
                    DataBaseRepository.com35++;
                    DataBaseRepository.intSingle3++;
                    DataBaseRepository.intSingle5 += 2;
                    //
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                } else if (diceStrHolder.startsWith("4")) {             //445
                    DataBaseRepository.com45++;
                    DataBaseRepository.intSingle5++;
                    DataBaseRepository.intSingle4 += 2;
                    //
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                } else if (diceStrHolder.startsWith("2")) {             //256
                    DataBaseRepository.com25++;
                    DataBaseRepository.com26++;
                    DataBaseRepository.com56++;
                    DataBaseRepository.intSingle2++;
                    DataBaseRepository.intSingle5++;
                    DataBaseRepository.intSingle6++;
                    DataBaseRepository.intF2356++;
                    DataBaseRepository.intF2356ns = 0;
                    //
                    DataBaseRepository.anyThree++;
                    DataBaseRepository.anyThreeDiff++;
                    DataBaseRepository.anyDoubleDiff = 0;
                } else if (diceStrHolder.startsWith("34")) {            //346
                    DataBaseRepository.com34++;
                    DataBaseRepository.com36++;
                    DataBaseRepository.com46++;
                    DataBaseRepository.intSingle3++;
                    DataBaseRepository.intSingle4++;
                    DataBaseRepository.intSingle6++;
                    DataBaseRepository.intF3456++;
                    DataBaseRepository.intF3456ns = 0;
                    //
                    DataBaseRepository.anyThree++;
                    DataBaseRepository.anyThreeDiff++;
                    DataBaseRepository.anyDoubleDiff = 0;
                }
                break;
            case 12:
                if (diceStrHolder.startsWith("4")) {                    //444
                    //444
                    DataBaseRepository.intBig++;
                    DataBaseRepository.intBigDiff = 0;
                    DataBaseRepository.intSmallDiff = 0;
                    //
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                    //
                    DataBaseRepository.intEven++;
                    DataBaseRepository.intOddDiff = 0;
                    DataBaseRepository.intEvenDiff = 0;

                    DataBaseRepository.intSingle4 += 3;
                    DataBaseRepository.intTriple4++;
                    DataBaseRepository.anyTriple++;

                } else {
                    DataBaseRepository.intBig++;
                    DataBaseRepository.intBigDiff++;
                    DataBaseRepository.intSmallDiff = 0;
                    //
                    DataBaseRepository.intEven++;
                    DataBaseRepository.intEvenDiff++;
                    DataBaseRepository.intOddDiff = 0;

                    if (diceStrHolder.startsWith("1")) {   //156
                        DataBaseRepository.com15++;
                        DataBaseRepository.com16++;
                        DataBaseRepository.com56++;
                        DataBaseRepository.intSingle1++;
                        DataBaseRepository.intSingle5++;
                        DataBaseRepository.intSingle6++;
                        //
                        DataBaseRepository.anyThree++;
                        DataBaseRepository.anyThreeDiff++;
                        DataBaseRepository.anyDoubleDiff = 0;
                    } else if (diceStrHolder.startsWith("24")) { //246
                        DataBaseRepository.com24++;
                        DataBaseRepository.com26++;
                        DataBaseRepository.com46++;
                        DataBaseRepository.intSingle4++;
                        DataBaseRepository.intSingle2++;
                        DataBaseRepository.intSingle6++;
                        //
                        DataBaseRepository.anyThree++;
                        DataBaseRepository.anyThreeDiff++;
                        DataBaseRepository.anyDoubleDiff = 0;
                    } else if (diceStrHolder.startsWith("34")) {     //345
                        DataBaseRepository.com34++;
                        DataBaseRepository.com35++;
                        DataBaseRepository.com45++;
                        DataBaseRepository.intSingle4++;
                        DataBaseRepository.intSingle3++;
                        DataBaseRepository.intSingle5++;
                        DataBaseRepository.intF3456++;
                        DataBaseRepository.intF3456ns = 0;
                        DataBaseRepository.intF2345++;
                        DataBaseRepository.intF2345ns = 0;
                        //
                        DataBaseRepository.anyThree++;
                        DataBaseRepository.anyThreeDiff++;
                        DataBaseRepository.anyDoubleDiff = 0;
                    } else if (diceStrHolder.startsWith("25")) {    //255
                        DataBaseRepository.com25++;
                        DataBaseRepository.intSingle2++;
                        DataBaseRepository.intSingle5 += 2;
                        //
                        DataBaseRepository.anyDouble++;
                        DataBaseRepository.anyDoubleDiff++;
                        DataBaseRepository.anyThreeDiff = 0;
                    } else if (diceStrHolder.startsWith("33")) {     //336
                        DataBaseRepository.com36++;
                        DataBaseRepository.intSingle6++;
                        DataBaseRepository.intSingle3 += 2;
                        //
                        DataBaseRepository.anyDouble++;
                        DataBaseRepository.anyDoubleDiff++;
                        DataBaseRepository.anyThreeDiff = 0;
                    }
                }
                DataBaseRepository.sum12++;
                break;
            case 11:
                DataBaseRepository.intBig++;
                DataBaseRepository.intBigDiff++;
                DataBaseRepository.intSmallDiff = 0;
                //
                DataBaseRepository.intOdd++;
                DataBaseRepository.intOddDiff++;
                DataBaseRepository.intEvenDiff = 0;
                //
                DataBaseRepository.sum11++;
                if (diceStrHolder.startsWith("15")) {               //155
                    DataBaseRepository.com15++;
                    DataBaseRepository.intSingle5 += 2;
                    DataBaseRepository.intSingle1++;
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;

                } else if (diceStrHolder.startsWith("34")) {       //344
                    DataBaseRepository.com34++;
                    DataBaseRepository.intSingle3++;
                    DataBaseRepository.intSingle4 += 2;
                    //
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                } else if (diceStrHolder.startsWith("33")) {     //335
                    DataBaseRepository.com35++;
                    DataBaseRepository.intSingle5++;
                    DataBaseRepository.intSingle3 += 2;
                    //
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                } else if (diceStrHolder.startsWith("24")) {   //245
                    DataBaseRepository.com25++;
                    DataBaseRepository.com24++;
                    DataBaseRepository.com45++;
                    DataBaseRepository.intSingle2++;
                    DataBaseRepository.intSingle5++;
                    DataBaseRepository.intSingle4++;
                    DataBaseRepository.intF2345++;
                    DataBaseRepository.intF2345ns = 0;
                    //
                    DataBaseRepository.anyThree++;
                    DataBaseRepository.anyThreeDiff++;
                    DataBaseRepository.anyDoubleDiff = 0;
                } else if (diceStrHolder.startsWith("14")) {    //146
                    DataBaseRepository.com14++;
                    DataBaseRepository.com16++;
                    DataBaseRepository.com46++;
                    DataBaseRepository.intSingle1++;
                    DataBaseRepository.intSingle4++;
                    DataBaseRepository.intSingle6++;
                    //
                    DataBaseRepository.anyThree++;
                    DataBaseRepository.anyThreeDiff++;
                    DataBaseRepository.anyDoubleDiff = 0;
                } else if (diceStrHolder.startsWith("23")) {    //236
                    DataBaseRepository.com23++;
                    DataBaseRepository.com36++;
                    DataBaseRepository.com26++;
                    DataBaseRepository.intSingle3++;
                    DataBaseRepository.intSingle2++;
                    DataBaseRepository.intSingle6++;
                    DataBaseRepository.intF2356++;
                    DataBaseRepository.intF2356ns = 0;
                    //
                    DataBaseRepository.anyThree++;
                    DataBaseRepository.anyThreeDiff++;
                    DataBaseRepository.anyDoubleDiff = 0;
                }
                break;
            case 10:
                DataBaseRepository.intSmall++;
                DataBaseRepository.intSmallDiff++;
                DataBaseRepository.intBigDiff = 0;
                //
                DataBaseRepository.intEven++;
                DataBaseRepository.intEvenDiff++;
                DataBaseRepository.intOddDiff = 0;
                //
                DataBaseRepository.sum10++;
                if (diceStrHolder.startsWith("22")) {    //226
                    DataBaseRepository.com26++;
                    DataBaseRepository.intSingle2 += 2;
                    DataBaseRepository.intSingle6++;
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;

                } else if (diceStrHolder.startsWith("24")) {    //244
                    DataBaseRepository.com24++;
                    DataBaseRepository.intSingle2++;
                    DataBaseRepository.intSingle4 += 2;
                    //
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                } else if (diceStrHolder.startsWith("33")) {     //334
                    DataBaseRepository.com34++;
                    DataBaseRepository.intSingle4++;
                    DataBaseRepository.intSingle3 += 2;
                    //
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                } else if (diceStrHolder.startsWith("23")) {     //235
                    DataBaseRepository.com23++;
                    DataBaseRepository.com25++;
                    DataBaseRepository.com35++;
                    DataBaseRepository.intSingle2++;
                    DataBaseRepository.intSingle5++;
                    DataBaseRepository.intSingle3++;
                    DataBaseRepository.intF2345++;
                    DataBaseRepository.intF2345ns = 0;
                    DataBaseRepository.intF2356++;
                    DataBaseRepository.intF2356ns = 0;
                    //
                    DataBaseRepository.anyThree++;
                    DataBaseRepository.anyThreeDiff++;
                    DataBaseRepository.anyDoubleDiff = 0;
                } else if (diceStrHolder.startsWith("13")) {     //136
                    DataBaseRepository.com13++;
                    DataBaseRepository.com16++;
                    DataBaseRepository.com36++;
                    DataBaseRepository.intSingle1++;
                    DataBaseRepository.intSingle3++;
                    DataBaseRepository.intSingle6++;
                    //
                    DataBaseRepository.anyThree++;
                    DataBaseRepository.anyThreeDiff++;
                    DataBaseRepository.anyDoubleDiff = 0;
                } else if (diceStrHolder.startsWith("14")) {      //145
                    DataBaseRepository.com14++;
                    DataBaseRepository.com15++;
                    DataBaseRepository.com45++;
                    DataBaseRepository.intSingle1++;
                    DataBaseRepository.intSingle4++;
                    DataBaseRepository.intSingle5++;
                    //
                    DataBaseRepository.anyThree++;
                    DataBaseRepository.anyThreeDiff++;
                    DataBaseRepository.anyDoubleDiff = 0;
                }
                break;
            case 9:
                if (diceStrHolder.startsWith("3")) {
                    //333
                    DataBaseRepository.intSmall++;
                    DataBaseRepository.intBigDiff = 0;
                    DataBaseRepository.intSmallDiff = 0;
                    //
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                    //
                    DataBaseRepository.intOdd++;
                    DataBaseRepository.intOddDiff = 0;
                    DataBaseRepository.intEvenDiff = 0;

                    DataBaseRepository.intSingle3 += 3;
                    DataBaseRepository.intTriple3++;
                    DataBaseRepository.anyTriple++;

                } else {
                    DataBaseRepository.intSmall++;
                    DataBaseRepository.intSmallDiff++;
                    DataBaseRepository.intBigDiff = 0;
                    //
                    DataBaseRepository.intOdd++;
                    DataBaseRepository.intOddDiff++;
                    DataBaseRepository.intEvenDiff = 0;

                    if (diceStrHolder.startsWith("13")) {       //135
                        DataBaseRepository.com13++;
                        DataBaseRepository.com15++;
                        DataBaseRepository.com35++;
                        DataBaseRepository.intSingle1++;
                        DataBaseRepository.intSingle5++;
                        DataBaseRepository.intSingle3++;
                        //
                        DataBaseRepository.anyThree++;
                        DataBaseRepository.anyThreeDiff++;
                        DataBaseRepository.anyDoubleDiff = 0;
                    } else if (diceStrHolder.startsWith("23")) {   //234
                        DataBaseRepository.com23++;
                        DataBaseRepository.com24++;
                        DataBaseRepository.com34++;
                        DataBaseRepository.intSingle4++;
                        DataBaseRepository.intSingle2++;
                        DataBaseRepository.intSingle3++;
                        DataBaseRepository.intF2345++;
                        DataBaseRepository.intF2345ns = 0;
                        DataBaseRepository.intF1234++;
                        DataBaseRepository.intF1234ns = 0;
                        //
                        DataBaseRepository.anyThree++;
                        DataBaseRepository.anyThreeDiff++;
                        DataBaseRepository.anyDoubleDiff = 0;
                    } else if (diceStrHolder.startsWith("12")) {   //126
                        DataBaseRepository.com12++;
                        DataBaseRepository.com16++;
                        DataBaseRepository.com26++;
                        DataBaseRepository.intSingle1++;
                        DataBaseRepository.intSingle2++;
                        DataBaseRepository.intSingle6++;
                        //
                        DataBaseRepository.anyThree++;
                        DataBaseRepository.anyThreeDiff++;
                        DataBaseRepository.anyDoubleDiff = 0;
                    } else if (diceStrHolder.startsWith("22")) {   //225
                        DataBaseRepository.com25++;
                        DataBaseRepository.intSingle5++;
                        DataBaseRepository.intSingle2 += 2;
                        //
                        DataBaseRepository.anyDouble++;
                        DataBaseRepository.anyDoubleDiff++;
                        DataBaseRepository.anyThreeDiff = 0;
                    } else if (diceStrHolder.startsWith("14")) {   //144
                        DataBaseRepository.com14++;
                        DataBaseRepository.intSingle1++;
                        DataBaseRepository.intSingle4 += 2;
                        //
                        DataBaseRepository.anyDouble++;
                        DataBaseRepository.anyDoubleDiff++;
                        DataBaseRepository.anyThreeDiff = 0;
                    }
                }
                DataBaseRepository.sum9++;
                break;
            case 8:
                DataBaseRepository.intSmall++;
                DataBaseRepository.intSmallDiff++;
                DataBaseRepository.intBigDiff = 0;
                //
                DataBaseRepository.intEven++;
                DataBaseRepository.intEvenDiff++;
                DataBaseRepository.intOddDiff = 0;
                //
                DataBaseRepository.sum8++;
                if (diceStrHolder.startsWith("11")) {           //116
                    DataBaseRepository.com16++;
                    DataBaseRepository.intSingle1 += 2;
                    DataBaseRepository.intSingle6++;
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;

                } else if (diceStrHolder.startsWith("22")) {    //224
                    DataBaseRepository.com24++;
                    DataBaseRepository.intSingle4++;
                    DataBaseRepository.intSingle2 += 2;
                    //
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                } else if (diceStrHolder.startsWith("23")) {      //233
                    DataBaseRepository.com23++;
                    DataBaseRepository.intSingle2++;
                    DataBaseRepository.intSingle3 += 2;
                    //
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                } else if (diceStrHolder.startsWith("12")) {     //125
                    DataBaseRepository.com12++;
                    DataBaseRepository.com15++;
                    DataBaseRepository.com25++;
                    DataBaseRepository.intSingle2++;
                    DataBaseRepository.intSingle5++;
                    DataBaseRepository.intSingle1++;
                    //
                    DataBaseRepository.anyThree++;
                    DataBaseRepository.anyThreeDiff++;
                    DataBaseRepository.anyDoubleDiff = 0;
                } else if (diceStrHolder.startsWith("13")) {        //134
                    DataBaseRepository.com13++;
                    DataBaseRepository.com14++;
                    DataBaseRepository.com34++;
                    DataBaseRepository.intSingle3++;
                    DataBaseRepository.intSingle4++;
                    DataBaseRepository.intSingle1++;
                    DataBaseRepository.intF1234++;
                    DataBaseRepository.intF1234ns = 0;
                    //
                    DataBaseRepository.anyThree++;
                    DataBaseRepository.anyThreeDiff++;
                    DataBaseRepository.anyDoubleDiff = 0;
                }
                break;
            case 7:
                DataBaseRepository.intSmall++;
                DataBaseRepository.intSmallDiff++;
                DataBaseRepository.intBigDiff = 0;
                //
                DataBaseRepository.intOdd++;
                DataBaseRepository.intOddDiff++;
                DataBaseRepository.intEvenDiff = 0;
                //
                DataBaseRepository.sum7++;
                if (diceStrHolder.startsWith("11")) {     //115
                    DataBaseRepository.com15++;
                    DataBaseRepository.intSingle1 += 2;
                    DataBaseRepository.intSingle5++;
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;

                } else if (diceStrHolder.startsWith("12")) {        //124
                    DataBaseRepository.com12++;
                    DataBaseRepository.com14++;
                    DataBaseRepository.com24++;
                    DataBaseRepository.intSingle1++;
                    DataBaseRepository.intSingle2++;
                    DataBaseRepository.intSingle4++;
                    DataBaseRepository.intF1234++;
                    DataBaseRepository.intF1234ns = 0;
                    //
                    DataBaseRepository.anyThree++;
                    DataBaseRepository.anyThreeDiff++;
                    DataBaseRepository.anyDoubleDiff = 0;
                } else if (diceStrHolder.startsWith("22")) {        //223
                    DataBaseRepository.com23++;
                    DataBaseRepository.intSingle2 += 2;
                    DataBaseRepository.intSingle3++;
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                } else if (diceStrHolder.startsWith("13")) {    //133
                    DataBaseRepository.com13++;
                    DataBaseRepository.intSingle3 += 2;
                    DataBaseRepository.intSingle1++;
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                } else {
                }
                break;
            case 6:
                if (diceStrHolder.startsWith("2")) {
                    //222
                    DataBaseRepository.intSmall++;
                    DataBaseRepository.intBigDiff = 0;
                    DataBaseRepository.intSmallDiff = 0;
                    //
                    DataBaseRepository.anyDouble++;
                    DataBaseRepository.anyDoubleDiff++;
                    DataBaseRepository.anyThreeDiff = 0;
                    //
                    DataBaseRepository.intEven++;
                    DataBaseRepository.intOddDiff = 0;
                    DataBaseRepository.intEvenDiff = 0;
                    DataBaseRepository.intSingle2 += 3;
                    DataBaseRepository.intTriple2++;
                    DataBaseRepository.anyTriple++;
                } else {
                    DataBaseRepository.intSmall++;
                    DataBaseRepository.intSmallDiff++;
                    DataBaseRepository.intBigDiff = 0;
                    //
                    DataBaseRepository.intEven++;
                    DataBaseRepository.intEvenDiff++;
                    DataBaseRepository.intOddDiff = 0;

                    if (diceStrHolder.startsWith("11")) {       //114
                        DataBaseRepository.anyDouble++;
                        DataBaseRepository.anyDoubleDiff++;
                        DataBaseRepository.anyThreeDiff = 0;
                        //
                        DataBaseRepository.com14++;
                        DataBaseRepository.intSingle1 += 2;
                        DataBaseRepository.intSingle4++;
                    } else {                                    //123
                        DataBaseRepository.com12++;
                        DataBaseRepository.com13++;
                        DataBaseRepository.com23++;
                        DataBaseRepository.intSingle1++;
                        DataBaseRepository.intSingle2++;
                        DataBaseRepository.intSingle3++;
                        DataBaseRepository.intF1234++;
                        DataBaseRepository.intF1234ns = 0;
                        //
                        DataBaseRepository.anyThree++;
                        DataBaseRepository.anyThreeDiff++;
                        DataBaseRepository.anyDoubleDiff = 0;
                    }
                }
                DataBaseRepository.sum6++;
                break;
            case 5:
                DataBaseRepository.intSmall++;
                DataBaseRepository.intSmallDiff++;
                DataBaseRepository.intBigDiff = 0;
                //
                DataBaseRepository.intOdd++;
                DataBaseRepository.intOddDiff++;
                DataBaseRepository.intEvenDiff = 0;
                //
                DataBaseRepository.anyDouble++;
                DataBaseRepository.anyDoubleDiff++;
                DataBaseRepository.anyThreeDiff = 0;
                //
                DataBaseRepository.sum4++;
                if (diceStrHolder.startsWith("11")) {       //113
                    DataBaseRepository.com13++;
                    DataBaseRepository.intSingle1 += 2;
                    DataBaseRepository.intSingle3++;
                } else {                                    //122
                    DataBaseRepository.com12++;
                    DataBaseRepository.intSingle2 += 2;
                    DataBaseRepository.intSingle1++;
                }
                break;
            case 4:                                             //112
                DataBaseRepository.intSmall++;
                DataBaseRepository.intSmallDiff++;
                DataBaseRepository.intBigDiff = 0;
                //
                DataBaseRepository.intEven++;
                DataBaseRepository.intEvenDiff++;
                DataBaseRepository.intOddDiff = 0;
                //
                DataBaseRepository.anyDouble++;
                DataBaseRepository.anyDoubleDiff++;
                DataBaseRepository.anyThreeDiff = 0;
                //
                DataBaseRepository.intSingle1 += 2;
                DataBaseRepository.intSingle2++;
                //
                DataBaseRepository.sum4++;
                //
                DataBaseRepository.com12++;
                break;
            case 3:
                //111
                DataBaseRepository.intSmall++;
                DataBaseRepository.intBigDiff = 0;
                DataBaseRepository.intSmallDiff = 0;
                //
                DataBaseRepository.anyDouble++;
                DataBaseRepository.anyDoubleDiff++;
                DataBaseRepository.anyThreeDiff = 0;
                //
                DataBaseRepository.intOdd++;
                DataBaseRepository.intOddDiff = 0;
                DataBaseRepository.intEvenDiff = 0;

                DataBaseRepository.intSingle1 += 3;
                DataBaseRepository.intTriple1++;
                DataBaseRepository.anyTriple++;
                break;
            default:
                System.err.println("Never Display!");
                break;
        }// end switch
    }

    private static void sortArray(int[] diceArr, int low, int high) {
        int i = low;
        int j = high;
        int y = 0;
        int z = diceArr[(low + high) / 2];

        do {
            while (diceArr[i] < z) {
                i++;
            }

            while (diceArr[j] > z) {
                j--;
            }

            if (i <= j) {
                y = diceArr[i];
                diceArr[i] = diceArr[j];
                diceArr[j] = y;
                i++;
                j--;
            }
        } while (i <= j);

        if (low < j) {
            sortArray(diceArr, low, j);
        }

        if (i < high) {
            sortArray(diceArr, i, high);
        }
    }
}
