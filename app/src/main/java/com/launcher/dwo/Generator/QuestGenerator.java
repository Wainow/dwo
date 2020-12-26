package com.launcher.dwo.Generator;

import android.util.Log;

import com.launcher.dwo.Quest.Quest;

import java.util.ArrayList;
import java.util.Random;

public class QuestGenerator {

    public QuestGenerator(){
    }

    public String GenerateDescription(){
        StringBuilder description = new StringBuilder();
        Task task = new Task();
        Start start = new Start();
        String place = start.getStartPlace();
        int distance = start.getDistance();
        int difficulty = start.getDifficulty();
        String difficultyString = start.getStringDifficulty(difficulty);
        Random random = new Random();
        int roll = Math.abs((int) random.nextGaussian()*10 + 1);
        description.append(GenerateCustomer() + "\n");
        description.append("start place: " + place + "\n" + start.getStringDistance(distance) + "\n" + difficultyString + "\n");
        description.append("task: " + task.getTask() + "\n" + task.getSeveralDescriptions(roll) + "\n");
        description.append(new Reward().getReward(distance, difficulty));

        return description.toString();
    }

    public String GenerateName(){
        QuestName questName = new QuestName();
        return questName.getQuestName();
    }

    public Quest GenerateQuest(){
        return new Quest(
                GenerateName(),
                GenerateDescription(),
                ""
        );
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

    private static class QuestName{
        private ArrayList<String> names;
        private ArrayList<String> adjectives;
        private ArrayList<String> noun;

        private QuestName(){
            names = new ArrayList<>();
            adjectives = new ArrayList<>();
            noun = new ArrayList<>();

            setDefaultAdjectives();
            setDefaultNames();
            setDefaultNouns();
        }

        private String getQuestName(){
            return getName() + " " + getAdjective() + " " + getNoun();
        }

        private String getName(){
            int roll = (int) (Math.random() * names.size()-1);
            return names.get(roll);
        }

        public String getAdjective() {
            int roll = (int) (Math.random() * adjectives.size()-1);
            return adjectives.get(roll);
        }

        public String getNoun(){
            int roll = (int) (Math.random() * noun.size()-1);
            return noun.get(roll);
        }

        private void setDefaultNames(){
            names.add("Royal's");
            names.add("Peasant's");
            names.add("Witcher's");
            names.add("Warrior's");
            names.add("Rower's");
            names.add("Dragon's");
            names.add("Knight's");
            names.add("Mag's");
            names.add("Org's");
            names.add("Dwarf's");
            names.add("Human's");
            names.add("Genasi's");
            names.add("Awakened's");
            names.add("Vampire's");
            names.add("Dragon-born's");
            names.add("Undead's");
            names.add("Daughter's");
            names.add("Mother's");
            names.add("Father's");
            names.add("Nan's");
            names.add("Grandparent's");
            names.add("Elf's");
            names.add("Pet's");
            names.add("Goblin's");
            names.add("Merchant's");
            names.add("Scholar's");
            names.add("Kid's");
            names.add("Adult's");
            names.add("Hero's");
            names.add("Villain's");
            names.add("Animal's");
            names.add("Farmer's");
            names.add("Doctor's");
            names.add("Blacksmith's");
            names.add("Guard's");
            names.add("Bartender's");
            names.add("Waiter's");
            names.add("Whore's");
            names.add("Fortune teller's");
            names.add("Fraudster's");
            names.add("Thief's");
            names.add("Homeless's");
            names.add("Student's");
            names.add("Murderer's");
            names.add("Kidnapper's");
            names.add("Arsonist's");
            names.add("Healer's");
            names.add("Saint's");
            names.add("Ghost's");
            names.add("Monster's");
            names.add("Fairy-tale character's");
            names.add("Imaginary character's");
        }

        private void setDefaultAdjectives(){
            adjectives.add("beautiful");
            adjectives.add("terrible");
            adjectives.add("frightening");
            adjectives.add("smelly");
            adjectives.add("dirty");
            adjectives.add("ugly");
            adjectives.add("slimy");
            adjectives.add("strange");
            adjectives.add("invisible");
            adjectives.add("magic");
            adjectives.add("torn");
            adjectives.add("worn");
            adjectives.add("exhausted");
            adjectives.add("sparkling");
            adjectives.add("incredible");
            adjectives.add("cool");
            adjectives.add("stupid");
            adjectives.add("sweet");
            adjectives.add("delicious");
            adjectives.add("bitter");
            adjectives.add("sour");
            adjectives.add("moronic");
            adjectives.add("stupid");
            adjectives.add("slop");
            adjectives.add("hell");
            adjectives.add("paradise");
            adjectives.add("magic");
            adjectives.add("flying");
            adjectives.add("silk");
            adjectives.add("iron");
            adjectives.add("gear");
            adjectives.add("solid");
            adjectives.add("soft");
            adjectives.add("gentle");
            adjectives.add("edible");
            adjectives.add("brown");
            adjectives.add("blue");
            adjectives.add("yellow");
            adjectives.add("red");
            adjectives.add("purple");
            adjectives.add("white");
            adjectives.add("black");
            adjectives.add("foreign");
            adjectives.add("Royal");
            adjectives.add("arab");
            adjectives.add("dirty");
            adjectives.add("dusted");
            adjectives.add("fucked");
            adjectives.add("cocks");
            adjectives.add("chic");
            adjectives.add("wonderful");
            adjectives.add("transparent");
            adjectives.add("unimaginable");
            adjectives.add("passionate");
            adjectives.add("sexy");
            adjectives.add("gay");
            adjectives.add("faggot");
            adjectives.add("ass");
            adjectives.add("leather");
            adjectives.add("general");
            adjectives.add("officer");
            adjectives.add("soldier");
            adjectives.add("hero");
            adjectives.add("military");
            adjectives.add("coconut");
            adjectives.add("banana");
            adjectives.add("apple");
            adjectives.add("meat");
            adjectives.add("bony");
            adjectives.add("selfish");
            adjectives.add("loving");
            adjectives.add("jealous");
            adjectives.add("temporary");
            adjectives.add("understanding");
            adjectives.add("maternal");
            adjectives.add("talking");
            adjectives.add("screaming");
            adjectives.add("water");
            adjectives.add("ice");
            adjectives.add("swamp");
            adjectives.add("sand");
            adjectives.add("stolen");
            adjectives.add("lovers");
            adjectives.add("returning");
            adjectives.add("poison");
            adjectives.add("animal");
            adjectives.add("smart");
            adjectives.add("genius");
            adjectives.add("stitched");
            adjectives.add("chewed");
            adjectives.add("bitten");
            adjectives.add("torn");
            adjectives.add("inhuman");
            adjectives.add("rotten");
            adjectives.add("shitty");
            adjectives.add("new");
            adjectives.add("old");
            adjectives.add("young");
            adjectives.add("men");
            adjectives.add("women");
        }

        private void setDefaultNouns(){
            noun.add("roll");
            noun.add("banana");
            noun.add("boot");
            noun.add("sword");
            noun.add("spear");
            noun.add("axe");
            noun.add("true");
            noun.add("lie");
            noun.add("experiment");
            noun.add("road");
            noun.add("word");
            noun.add("crown");
            noun.add("shit");
            noun.add("cum");
            noun.add("honor");
            noun.add("eye");
            noun.add("eye view");
            noun.add("coin");
            noun.add("friend");
            noun.add("enemy");
            noun.add("belly");
            noun.add("leg");
            noun.add("sense");
            noun.add("horse");
            noun.add("stash");
            noun.add("color");
            noun.add("potion");
            noun.add("hoof");
            noun.add("horns");
            noun.add("grass");
            noun.add("food");
            noun.add("luxury");
            noun.add("pillar");
            noun.add("weapons");
            noun.add("forest");
            noun.add("girlfriend");
            noun.add("mistress");
            noun.add("queen");
            noun.add("prince");
            noun.add("king");
            noun.add("thief");
            noun.add("knight");
            noun.add("magician");
            noun.add("jet");
            noun.add("fire");
            noun.add("water");
            noun.add("meat");
            noun.add("shield");
            noun.add("armor");
            noun.add("work");
            noun.add("word");
            noun.add("puddle");
            noun.add("luck");
            noun.add("failure");
            noun.add("fear");
            noun.add("courage");
            noun.add("blood");
            noun.add("vomit");
            noun.add("alcohol");
            noun.add("underpants");
            noun.add("condom");
            noun.add("genitals");
            noun.add("egg");
            noun.add("eggs");
            noun.add("mountain");
            noun.add("land");
            noun.add("inhale");
            noun.add("exhale");
            noun.add("song");
            noun.add("bird");
            noun.add("turtle");
            noun.add("animal");
            noun.add("shark");
            noun.add("parrot");
            noun.add("alien");
            noun.add("potion");
            noun.add("blow");
            noun.add("news");
            noun.add("rumor");
            noun.add("dungeon");
            noun.add("dragon");
            noun.add("nature");
            noun.add("death");
            noun.add("courage");
            noun.add("reward");
            noun.add("ugliness");
            noun.add("beauty");
            noun.add("love");
            noun.add("excitement");
            noun.add("scales");
            noun.add("magic");
            noun.add("danger");
            noun.add("resurrection");
            noun.add("resilience");
            noun.add("mood");
            noun.add("contest");
            noun.add("information");
            noun.add("math");
            noun.add("shovel");
            noun.add("axe");
            noun.add("ruler");
            noun.add("wood");
            noun.add("iron");
            noun.add("gold");
            noun.add("diamond");
            noun.add("ball");
            noun.add("circle");
            noun.add("garbage");
            noun.add("dump");
            noun.add("dirt");
            noun.add("nails");
            noun.add("hair skin");
            noun.add("mouth");
            noun.add("hands");
            noun.add("toes");
            noun.add("socks");
            noun.add("shirt");
            noun.add("notebook");
            noun.add("paper");
            noun.add("pen");
            noun.add("cone");
            noun.add("cardboard");
            noun.add("map");
            noun.add("mystery");
            noun.add("wall");
            noun.add("castle");
            noun.add("cap");
            noun.add("color");
            noun.add("cold");
            noun.add("winter");
            noun.add("strangeness");
            noun.add("light");
            noun.add("candle");
            noun.add("dark");
            noun.add("coffee");
            noun.add("drink");
            noun.add("lion");
            noun.add("music");
            noun.add("science");
            noun.add("anaconda");
            noun.add("crocodile");
            noun.add("drug");
            noun.add("carpet");
            noun.add("material");
            noun.add("glass");
            noun.add("shard");
            noun.add("dress");
            noun.add("cloth");
            noun.add("clothing");
            noun.add("tea");
            noun.add("mission");
            noun.add("task");
            noun.add("justification");
            noun.add("quest");
        }
    }
}
