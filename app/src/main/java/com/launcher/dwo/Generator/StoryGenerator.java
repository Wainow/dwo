package com.launcher.dwo.Generator;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class StoryGenerator {
    private String role;
    private String composition;

    public StoryGenerator(){
        this.role = "Evil";
        composition = "";
    }

    public StoryGenerator(int role){
        switch (role){
            case 1: this.role = "Knight";
            composition = "Among other things ";
            break;
            case 2: this.role = "Mag";
            composition = "Among other things ";
            break;
            case 3: this.role = "Rower";
            composition = "Among other things ";
            break;
            case 4: this.role = "Thief";
            composition = "Among other things ";
            break;
            default: this.role = "Unknown";
            composition = "";
        }
    }

    public String GenerateStory(){
        StoryData storyData = StoryData.newInstance();
        WorldView worldView = new WorldView();
        RoleStoryData roleStoryData = new RoleStoryData(role);

        int choiceStoryData = (int) (Math.random() * 5 );
        int choiceWorldView = (int) (Math.random() * 8 +1);

        String part1 = storyData.getStory(choiceStoryData);
        String part2 = roleStoryData.getClassStory(role);
        String part3 = worldView.getWorldView(choiceWorldView);

        return part1 + "\n" + composition + part2 + "\n\n" + part3;
    }

    private static class StoryData{
        private ArrayList<ArrayList<String>> all_stories;
        private ArrayList<String> stories1;
        private ArrayList<String> stories2;
        private ArrayList<String> stories3;
        private ArrayList<String> stories4;
        private ArrayList<String> stories5;

        private StoryData(){
            all_stories = new ArrayList<>();
            stories5 = new ArrayList<>();
            stories4 = new ArrayList<>();
            stories3 = new ArrayList<>();
            stories2 = new ArrayList<>();
            stories1 = new ArrayList<>();
            SetDefaultStories();
            all_stories.add(stories1);
            all_stories.add(stories2);
            all_stories.add(stories3);
            all_stories.add(stories4);
            all_stories.add(stories5);
        }

        private static StoryData newInstance() {
            return new StoryData();
        }

        private String getStory(int role_number){
            int index = (int) (Math.random()) * all_stories.get(role_number).size();
            return all_stories.get(role_number).get(index);
        }

        private void SetDefaultStories(){
            stories1.add("Your story begins in a small, poor village. You were the seventh child in the family, your family often starved, and every winter you buried one of your relatives who died of hunger and the deadly cold. When you got a little older, and your parents realized that you can take care of yourself, they kicked you out of the door, looking for a better life.");
            stories1.add("You are an orphan; you didn't have friends or acquaintances who would care about you. From the birth you learned to beg and steal leftovers from the tables of wealthy people. When the last people left the poor quarter of your city, you realized that there was no point in being here anymore and decided to go in search of new adventures.");
            stories1.add("When you arrived in a foreign country, you were still a child; your parents were killed by robbers, and you were sold into slavery. All your childhood you passed from one master to another. The hope of release was melting every day, until one stormy night, a guard with a bunch of keys decided to take a nap near your cage. Taking the opportunity, you sneaked past him and decided to run into the night forest, to the accompaniment of thunderclaps.");
            stories2.add("You were part of an ancient family, which by its end had completely become impoverished and practically disappeared from the face of the earth. Every day the financial situation of your family was getting worse and worse. It got to the point that your father began to fire all the servants and began to save on everything. His attempts to save your family ended up in his grave. You did not want to repeat his fate, and one cloudy day you decided to leave the family nest forever, in the hope that luck would smile at you, and you will return the former greatness to your family.");
            stories2.add("During wars and plague epidemics, it is great luck to be born in the city, but your city could hardly hold back the attack of even the smallest army. Unfortunately, he could not withstand the onslaught of the enemy, and the soldiers who broke through destroyed everything in their path. But you were lucky again, you were not noticed among the corpses of your relatives, and soon, the enemy army left the ashen fragments of the once existing city. You left this place behind them...");
            stories2.add("You were born right on the battlefield, to the thunder of shells, the clang of metal and furious screams. When the carnage was over, you were found in your mother's dead arms. Not knowing how to handle the child, the mercenaries decided to keep you in order to join their ranks in the future. But these dreams were not destined to come true: the battle with numerous troops of the enemy was the last for your adoptive parents. Looking around the battlefield, you realized that you were completely alone in this cold and cruel world, but at the same time, you realized that it was your turn to seek adventure.");
            stories3.add("You were born into an ordinary peasant family, your daily routine was mundane and measured. You plowed the land during the day and cleaned the paddocks in the evening. Before going to bed, your mother read you tales of brave knights, mighty wizards, well-aimed marksmen and cunning robbers. These stories made an indelible impression on you, and you dreamed of how you would become the hero of one of these fairy tales, but your father was categorically against it, and saw in you someone to whom he could transfer his farm, because without him your family would die of hunger. For the first time in your life, you went against your father's wishes and decided to become an adventurer, naturally without telling him your intentions. Late at night, you stepped out of your doorstep and walked along the country road, leaving behind all doubts and regrets, to meet your fate.");
            stories3.add("You were born into a blacksmith family and all your hobbies were connected with the help of your father. Once an adventurer came to your shop and asked your father to forge him the strongest and sharpest blade. Despite the fact that the old blacksmith had not forged anything more complicated than a horseshoe for a long time, he did an excellent job. The beauty of this blade amazed you so much that you couldn't think of anything better how to become an adventurer yourself and go in search of the worthiest and beautiful weapon.");
            stories3.add("You were born and lived in a traveling circus. You have traveled to different cities and countries, earning your living by showing tricks and wonders of acrobatics. Sometimes you ate, sometimes not, but despite all the difficulties, you were one circus family. it seemed to you that there are no problems that you cannot solve. unfortunately, this confidence was only an illusion. When you were returning from the bazaar late at night, you noticed the burning dome of the circus. The fire spread quickly, and died out only in the morning. He ate all your belongings, all your friends. The fact that you survived is a great success, the flame of the fire left its mark on your soul, you decided to become an adventurer in order to find those who decided to break the thread of your quiet life and take cruel revenge on them.");
            stories4.add("Your father is the king, we can say that this is a gift of fate, but unfortunately, your mother was an ordinary washerwoman at the king's court, naturally, being a bastard, you will not receive absolutely anything that is due to the heirs of the king, moreover, immediately after your birth, you were taken away from the palace so that nothing could threaten your life. And yet, all the secret becomes clear, over time you found out your origin and decided to set off on a long journey, at the end of which was the throne of the king, which is most likely through the dead body of your father...");
            stories4.add("Your nightmares were different from other kids' nightmares. It was constantly repeated and was exactly the same: a huge black chthonic being that calls out to you from the darkest abyss. It asks you to free them, find the ancient temple, and break the seal. In the end, the call from the abyss finished you off completely, having equipped yourself on the way, you set off towards your destiny.");
            stories4.add("You were a small child when a strange old woman approached you in the city square and put a strange stone in your hands, promising that if you keep it with you until you come of age, you will become the most famous and powerful person in the world. The day of your majority has come, but the old woman's prediction never came true, you were very upset by this, and were about to throw the stone into the river, as suddenly it squeaked in your hand and you heard a voice inside your mind, which told you that in order to become the most a powerful person on earth, you need to sacrifice people, many people ...");
            stories5.add("You did not know your father, he left you with your mother when you were only four years old. Your mother never told you about your father, neither who he was, nor who he worked. “He had reasons for doing this,” she told you, but that was not enough. Once while rummaging through old things in the attic, you found a letter addressed to you, and your father wrote it. It said that your family was in danger and therefore he had to do everything to keep you safe, he also wrote that he wants to see you again and left the coordinates so that you find him. Without hesitation, уou hit the road ..");
            stories5.add("Once, many years ago, you were a glorious hero, everyone knew your name: from a poor man to a nobleman. There were many feats on your count, villains feared you and the inhabitants of cities and villages adored you. But at one point you fell asleep and did not wake up again. Awakening found you in an old and forgotten tomb, you realized that death overtook you in a dream, and you revived thanks to the experiments of one sorcerer who left you in this godforsaken place, believing that the experience was unsuccessful. Now you need to find answers to the questions that are spinning in your head, and the first of them will be: \"Who killed me?\"...");
            stories5.add("You have always loved painting, which is why your parents sent you to an art academy. Life inside her was measured and interesting, you comprehended the art of drawing and were simply fascinated by these things. But soon you noticed a very attractive portrait of a young and beautiful girl, he attracted you, you wanted to know as much as possible about this lady, but alas, no one knew either the author of the picture or the girl herself. You were very upset, realizing that you can hardly see her live, but when you come to your room in the evening, you find a note on your desk stating that the author of the painting lives in another city and is going to move soon, without hesitation. decided to go in search of the beauty from the picture ...");
        }
    }

    private class RoleStoryData{
        private String role;
        private HashMap<String, String> map;

        private RoleStoryData(String role){
            this.role = role;
            map = new HashMap<>();
            setDefaultClassStories();
        }

        private void setDefaultClassStories(){
            map.put("Knight", "you have always preferred to settle disputes by fighting in close combat using your strength and agility. You believed that the one who can win the duel is right, and thus be worthy of the award, whatever it may be...");
            map.put("Mag", "since childhood, you have experienced a craving for the unknown and the mystical. Suddenly discovered supernatural forces did not upset you, but on the contrary, gave you a clear understanding that you need to improve your newly acquired skills in order to become the greatest magician in the world…");
            map.put("Rower", "you have heard many stories and legends about marksmen who never missed. Every day you trained in accuracy to reach such heights. You have a dream inside of you to become famous as the most accurate archer ever…");
            map.put("Thief", "you have always had a craving for the appropriation of someone else's, nothing could be done with this feeling, and you decided to just go with the flow, first stealing small trinkets and sweet rolls, and then steal wallets and expensive jewelry...");
            map.put("Unknown", "");
            map.put("Evil", "");
        }

        private String getClassStory(String role){
            return map.get(role);
        }
    }

    private class WorldView{
        private HashMap<Integer, Pair<String, String>> map;

        private WorldView(){
            map = new HashMap<>();
            setDefaultWorldView();
        }

        private void setDefaultWorldView(){
            map.put(1, new Pair("Chaotic Evil", "Only fools follow the law and rules, being organized is not about you. You prefer chaos and destruction of any order, you can be described as an \"unpredictable egoist\" who will go to great lengths to achieve his target."));
            map.put(2, new Pair("Chaotic Neutral", "You have always valued your freedom more than the freedom of others, you are an ardent individualist who does not care about the traditions and opinions of people, nevertheless, you prefer not to face the guardians of the law."));
            map.put(3, new Pair("Neutral Evil", "The characters are primarily concerned with themselves and their own advancement. They have little objection to working with others, but often many of them go it alone. Their only interest is to move forward. If there is a quick and easy way to get a profit, be it legal, questionable, or apparently illegal, they'll take it."));
            map.put(4, new Pair("Lawful Evil", "The characters methodically get what they want, acting within the framework of their own moral code or within the framework of the laws, regardless of the suffering of others. The goal can be both personal gain (a typical example is an evil sorcerer trying to take over the world or part of it), and the welfare of society in the form in which a specific character understands it."));
            map.put(5, new Pair("True Neutral", "The character does not give preference to any of the paths, both in the choice between good and evil, and in the choice between chaos and order. A character of this type believes that good is better than evil, but for some reason cannot or does not want to follow the path of good, when this path requires any costs from him, be it money or the need to endanger his life for the sake of others."));
            map.put(6,new Pair("Neutral Good", "The characters believe balance of power is important, but the interests of order and chaos do not override the need for good. Since the universe is vast and inhabited by many creatures fighting for different goals, a determined pursuit of good will not upset the balance; it can even support him. If, in order to promote the development of good, it is necessary to support an organized society, then this is exactly what should be done."));
            map.put(7, new Pair("Lawful Neutral", "Neither believe in a strong and well-ordered government, be it tyranny or benevolent democracy. The benefits of being organized and systematized outweigh any moral issues raised by their activities."));
            map.put(8,new Pair("Lawful Good", "Characters with this personality believe that organized, strong societies with a well-functioning government can work to make life better for most people. To guarantee the quality of life, laws must be created and obeyed. When people respect the laws and try to help each other, society as a whole flourish."));
        }
        private String getWorldView(int number){
            return map.get(number).first + ": " + map.get(number).second;
        }
    }
}
