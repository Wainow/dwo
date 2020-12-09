package com.example.dwo;

import android.util.Log;
import android.widget.RelativeLayout;

import java.util.Random;

public class QuestGenerator {
    private String customer;

    public QuestGenerator(){
    }

    public String GenerateQuest(){
        StringBuilder quest = new StringBuilder();
        Task task = new Task();
        Start start = new Start();
        String place = start.getStartPlace();
        int distance = start.getDistance();
        int difficulty = start.getDifficulty();
        String difficultyString = start.getStringDifficulty(difficulty);
        Random random = new Random();
        int roll = Math.abs((int) random.nextGaussian()*10 + 1);
        quest.append(GenerateCustomer() + "\n");
        quest.append("start place: " + place + "\n" + start.getStringDistance(distance) + "\n" + difficultyString + "\n");
        quest.append("task: " + task.getTask() + "\n" + task.getSeveralDescriptions(roll) + "\n");
        quest.append(new Reward().getReward(distance, difficulty));

        return quest.toString();
    }

    public String GenerateCustomer(){
        return "customer: " + GenerateTemper() + ", " + GenerateAppearance() + ", " + GenerateRace() + " (" + GenerateAge() + " years looking)";
    }

    public String GenerateRace(){
        int roll = (int) (Math.random() * 100 + 1);
        Log.d("DebugLogs", "QuestGenerator: GenerateRace: roll: " + roll);
        if(roll <= 15){
            return "Human";
        } else if(roll <= 30){
            return "Elf";
        } else if(roll <= 45){
            return "Orc";
        } else if(roll <= 60){
            return "Dwarf";
        } else if(roll <= 70){
            return "Goblin";
        } else if(roll <= 80){
            return "Dragon-born";
        } else if(roll <= 87){
            return "Tiefling";
        } else if(roll <= 93){
            return "Awakened";
        } else if(roll <= 97){
            return "Genasi";
        } else{
            return "Vampire";
        }
    }

    public int GenerateAge(){
        return (int) (Math.random() * 200);
    }

    public String GenerateTemper(){
        int roll = (int) (Math.random() * 10 + 1);
        if(roll <= 3){
            return "Kind";
        } else if(roll <= 5){
            return "Neutral";
        } else if(roll <= 7){
            return "Sad";
        } else if(roll <= 9){
            return "Aggressive";
        } else{
            return "Tricky";
        }
    }

    public String GenerateAppearance(){
        int roll = (int) (Math.random() * 20 + 1);
        if(roll <= 3){
            return "Neat looking";
        } else if(roll <= 5){
            return "Ordinary looking";
        } else if(roll <= 7){
            return "Dirty looking";
        } else if(roll <= 9){
            return "Ugly looking and smelly";
        } else if(roll <= 11){
            return "Perfect looking, beautiful, statuesque and smelling good";
        } else if(roll <= 13){
            return "Beautiful but poor looking";
        } else if(roll <= 15){
            return "Ugly but rich looking";
        } else if(roll <= 17){
            return "Neat looking but has a mad look";
        } else if(roll <= 19){
            return "Looks like a creature posing as another race";
        } else{
            return "Looks like a real bag of shit";
        }
    }


    private static class Start{
        private Start(){}

        private String getStartPlace(){
            int roll = (int) (Math.random() * 2 + 1);
            Log.d("DebugLogs", "QuestGenerator: GetStartPlace: roll: " + roll);
            // setting start place: in city or out;
            if (roll == 1){
                return "In current city";
            } else{
                // in out: setting where: in residential place or not
                roll = (int) (Math.random() * 2 + 1);
                int distance = (int) (Math.random() * 200);
                if(roll == 1){
                    //not residential place: where exactly
                    roll = (int) (Math.random() * 10);
                    switch (roll){
                        case 0: return "In desert";
                        case 1: return "In swamp";
                        case 2: return "In forest";
                        case 3: return "In mountains";
                        case 4: return "In island";
                        case 5: return "In cave";
                        case 6: return "In plain";
                        case 7: return "In jungle";
                        case 8: return "In ruin";
                        case 9: return "In the magic place";
                        default: return "";
                    }
                } else{
                    // residential place: where exactly
                    roll = (int) (Math.random() * 3);
                    switch (roll){
                        case 0: return "In hostile territory";
                        case 1: return "In neutral territory";
                        case 2: return "In hostile-neutral forest";
                        default: return "";
                    }
                }
            }
        }

        private int getDistance(){
            Random random = new Random();
            int size = (int) (Math.random()*333);
            int roll = (int) (random.nextGaussian()*size + 100);
            return roll;
        }

        private String getStringDistance(int roll){
            if(roll < 0){
                return "distance: " + Math.abs(roll) + " km";
            }
            return "distance: " + roll + " km";
        }

        private int getDifficulty(){
            Random random = new Random();
            int roll = (int) (random.nextGaussian()*5);
            Log.d("DebugLogs", "getDifficulty: " + roll + " ");
            if(roll > 5)
                return 5;
            else if(roll < -5)
                return -5;
            return roll;
        }

        private String getStringDifficulty(int difficulty){
            switch (difficulty){
                case -5: return "difficulty: easiest (-5)";
                case -4: return "difficulty: easy (-4)";
                case -3: return "difficulty: more simplified (-3)";
                case -2: return "difficulty: simplified (-2)";
                case -1: return "difficulty: light (-1)";
                case 0: return "difficulty: normal (0)";
                case 1: return "difficulty: complicated (1)";
                case 2: return "difficulty: more complicated (2)";
                case 3: return "difficulty: hard (3)";
                case 4: return "difficulty: more harder (4)";
                case 5: return "difficulty: extremely hard(5)";
                default: return "";
            }
        }
    }

    private static class Task{
        private Task(){}

        private String getTask(){
            int roll = (int) (Math.random() * 2 + 1);
            if(roll == 1){
                // the task will be good
                return getGoodTask();
            } else{
                // the task will be bad
                return getBadTask();
            }
        }

        private String getGoodTask(){
            int roll = (int) (Math.random() * 22);
            Log.d("DebugLogs", "getGoodTask: " + roll + " ");
            switch (roll){
                case 0: return "The task of the quest is to save someone from something bad";
                case 1: return "The task of the quest is to cure someone from some disease or poison";
                case 2: return "The task of the quest is to bring some item from somewhere";
                case 3: return "The task of the quest is to punish someone bad";
                case 4: return "The task of the quest is to to return the stolen from somewhere";
                case 5: return "The task of the quest is to bury some dead";
                case 6: return "The task of the quest is to transfer some important item";
                case 7: return "The task of the quest is to repair some item";
                case 8: return "The task of the quest is cleaning somewhere";
                case 9: return "The task of the quest is raise  the mood someone";
                case 10: return "The task of the quest is grow something good";
                case 11: return "The task of the quest is participate in an good experiment/competition/quiz";
                case 12: return "The task of the quest is wear / try on some clothes";
                case 13: return "The task of the quest is read translate something";
                case 14: return "The task of the quest is cook /create / invent something good";
                case 15: return "The task of the quest is check some information (good)";
                case 16: return "The task of the quest is to find someone good";
                case 17: return "The task of the quest is accompany a group of creatures/one creature (good)";
                case 18: return "The task of the quest is to track someone good";
                case 19: return "The task of the quest is to to investigate something (maybe murder or something else)";
                case 20: return "The task of the quest is to secretly to destroy something";
                case 21: return "The task of the quest is to help the character with work";
                default: return "";
            }
        }

        private String getBadTask(){
            int roll = (int) (Math.random() * 24);
            Log.d("DebugLogs", "getBadTask: " + roll + " ");
            switch (roll){
                case 0: return "The task of the quest is to kill someone";
                case 1: return "The task of the quest is to steal some important item";
                case 2: return "The task of the quest is to poison someone";
                case 3: return "The task of the quest is to steal someone";
                case 4: return "The task of the quest is to punish someone good";
                case 5: return "The task of the quest is to bury someone alive";
                case 6: return "The task of the quest is to transfer some important item";
                case 7: return "The task of the quest is to scare someone";
                case 8: return "The task of the quest is to break something";
                case 9: return "The task of the quest is dirty something";
                case 10: return "The task of the quest is burn something";
                case 11: return "The task of the quest is work for someone else's profession";
                case 12: return "The task of the quest is disgrace someone";
                case 13: return "The task of the quest is spread some rumor";
                case 14: return "The task of the quest is scout something";
                case 15: return "The task of the quest is spoil the mood someone";
                case 16: return "The task of the quest is grow something bad";
                case 17: return "The task of the quest is participate in an bad experiment/competition/quiz";
                case 18: return "The task of the quest is cook/create / invent something bad";
                case 19: return "The task of the quest is check some information (bad)";
                case 20: return "The task of the quest is to find someone bad";
                case 21: return "The task of the quest is accompany a group of creatures/one creature (bad)";
                case 22: return "The task of the quest is to track someone bad";
                case 23: return "The task of the quest is secretly kill someone";
                default: return "";
            }
        }

        private String getDescription(){
            String description = "";
            int roll = (int) (Math.random() * 10);
            if(roll > 7){
                roll = (int) (Math.random() * 28);
                switch (roll){
                    case 0: description = "(it seems that although the task sounds like a good one, the characters will perform something terrible)";
                        break;
                    case 1: description = "(during the task, the heroes will meet the one who will pay more for not completing the task)";
                        break;
                    case 2: description = "(the customer will try to cheat and not return the reward)";
                        break;
                    case 3: description = "(the same task should be completed earlier/simultaneously with the heroes by another adventurer)";
                        break;
                    case 4: description = "(the opposite task is performing simultaneously with the heroes by another adventurer)";
                        break;
                    case 5: description = "(an emergency occurs in the environment during execution)";
                        break;
                    case 6: description = "(heroes are accosted by guards/hostile creatures during execution)";
                        break;
                    case 7: description = "(the task fails due to the death/loss/failure of the task object)";
                        break;
                    case 8: description = "(the customer will be dissatisfied with the result)";
                        break;
                    case 9: description = "(the customer mixed up something and the characters will not complete the task correctly)";
                        break;
                    case 10: description = "(the quest was a trap and the heroes will want to kill)";
                        break;
                    case 11: description = "(the customer has died or disappeared/ you will need to report on the quest to another person)";
                        break;
                    case 12: description = "(the customer forgot that he gave the quest and forgot what the characters look like)";
                        break;
                    case 13: description = "(the task is limited in time)";
                        break;
                    case 14: description = "(the target location is not allowed)";
                        break;
                    case 15: description = "(someone's important blood is involved)";
                        break;
                    case 16: description = "(target under guard)";
                        break;
                    case 17: description = "(the task will ruin your reputation )";
                        break;
                    case 18: description = "(the target is related to magic)";
                        break;
                    case 19: description = "(the case involves a cult / clan / group)";
                        break;
                    case 20: description = "(the case involves the resistance)";
                        break;
                    case 21: description = "(target to track down/find)";
                        break;
                    case 22: description = "(heroes can't leave any traces)";
                        break;
                    case 23: description = "(bad weather condition)";
                        break;
                    case 24: description = "(the undead are involved)";
                        break;
                    case 25: description = "(heroes need to act alone)";
                        break;
                    case 26: description = "(the target contradicts the moral principles of one of the characters)";
                        break;
                    case 27: description = "(target to track down/find)";
                        break;
                }
            } else if (roll > 5){
                roll = (int) (Math.random() * 10);
                switch (roll){
                    case 0: description = "(the customer will not even be interested in the result of the task and will give the money (perhaps even immediately when he gives the task))";
                        break;
                    case 1: description = "(someone has already completed the task for the heroes)";
                        break;
                    case 2: description = "(something will happen that will make the task much easier)";
                        break;
                    case 3: description = "(the customer forgot the reward and asks how much he owes the heroes)";
                        break;
                    case 4: description = "(during the task, something will happen that will give a clue to one of the stories of the heroes)";
                        break;
                    case 5: description = "(during the execution, the heroes will discover the stash)";
                        break;
                    case 6: description = "(the customer will give additional items to complete the task)";
                        break;
                    case 7: description = "(the customer died and bequeathed some inheritance to the heroes)";
                        break;
                    case 8: description = "(the customer will give important details of the task)";
                        break;
                    case 9: description = "(during the task, the enemies will be afraid of the heroes)";
                        break;
                }
            }
            return description;
        }

        private String getSeveralDescriptions(int count){
            StringBuilder description = new StringBuilder();
            for(int i = 1; i < count; i++){
                description.append("." + getDescription());
            }
            description.append(". " + getDescription());
            return "description: " + description;
        }
    }


    private static class Reward{
        private Reward(){}

        private String getReward(int distance, int difficulty){
            Log.d("DebugLogs", "getReward: distance" + distance);
            Log.d("DebugLogs", "getReward: difficulty" + difficulty);
            double rewardPoints = 0;
            distance = Math.abs(distance);
            if(distance > 233){
                rewardPoints += 5;
            } else if(distance >= 100){
                rewardPoints += 3;
            } else if(distance >= 33){
                rewardPoints += 1;
            } else if(distance >= 3){
                rewardPoints += 0.5;
            }
            Log.d("DebugLogs", "getReward: rewardPoints" + rewardPoints);
            switch (difficulty){
                case -5: rewardPoints += 0.0;
                    break;
                case -4: rewardPoints += 0.1;
                    break;
                case -3: rewardPoints += 0.2;
                    break;
                case -2: rewardPoints += 0.3;
                    break;
                case -1: rewardPoints += 0.4;
                    break;
                case 0: rewardPoints += 0.5;
                    break;
                case 1: rewardPoints += 1;
                    break;
                case 2: rewardPoints += 2;
                    break;
                case 3: rewardPoints += 3;
                    break;
                case 4: rewardPoints += 4;
                    break;
                case 5: rewardPoints += 5;
                    break;
            }
            Log.d("DebugLogs", "getReward: rewardPoints" + rewardPoints);
            if(rewardPoints >= 10){
                if((int) (Math.random()*2) == 1)
                    return "reward: legendary item";
                else
                    return "reward: a lot of money";
            } if(rewardPoints >= 6){
                if((int) (Math.random()*2) == 1)
                    return "reward: good item";
                else
                    return "reward: more that usually money";
            } if(rewardPoints >= 1.5){
                if((int) (Math.random()*2) == 1)
                    return "reward: normal item";
                else
                    return "reward: just money";
            } if(rewardPoints > 0.2){
                if((int) (Math.random()*2) == 1)
                    return "reward: bad item";
                else
                    return "reward: little money";
            } else{
                return "reward: trash";
            }
        }
    }
}
