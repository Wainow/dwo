package com.example.dwo.Generator;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemGenerator {
    private String role;

    public ItemGenerator() {
        this.role = "Evil";
    }

    public ItemGenerator(int role) {
        switch (role) {
            case 1:
                this.role = "Knight";
                break;
            case 2:
                this.role = "Mag";
                break;
            case 3:
                this.role = "Rower";
                break;
            case 4:
                this.role = "Thief";
                break;
            default:
                this.role = "Unknown";
        }
    }

    public String Generate() {
        ClothData clothData = new ClothData();
        WeaponData weaponData = new WeaponData();
        RoleItemData roleItemData = new RoleItemData(role);
        PotionData potionData = new PotionData();
        Trash trash = new Trash();

        String items[] = new String[10];

        StringBuilder inventory = new StringBuilder();

        items[0] = clothData.getItem();
        items[1] = clothData.getItem();
        items[2] = weaponData.getWeapon();
        items[3] = weaponData.getWeapon();
        items[4] = roleItemData.getRoleItem();
        items[5] = roleItemData.getRoleItem();
        items[6] = roleItemData.getRoleItem();
        items[7] = trash.getTrash();
        items[8] = trash.getTrash();
        items[9] = trash.getTrash();

        for(int i = 0; i < items.length-2; i++){
            if(!items[i].equals("")){
                inventory.append(items[i]).append(", ");
            }
        }
        inventory.append(items[items.length-1]);

        return inventory.toString();

    }

    private static class ClothData {
        private HashMap<Integer, Pair<String, String>> map;

        private ClothData() {
            map = new HashMap<>();
            SetDefaultItems();
        }

        private void SetDefaultItems() {
            addItem(50, "Worn jacket", "it was worn by people and time, but it still keeps warm.");
            addItem(45, "Old jacket", "older than it seems, but copes with its functions.");
            addItem(20, "Leather Jacket", "can warm up and take a couple of hits.");
            addItem(15, "Sturdy leather jacket", "made from the skin of an unknown animal.");
            addItem(14, "Black cloak", "dark and inconspicuous, it is worn by those who do not want to draw too much attention to themselves.");
            addItem(19, "Red cloak", "bright and catchy, it is worn by those who want to be at the center of any company.");
            addItem(21, "Worn Leather armor", "before you, this armor was worn by someone else, and worn very carelessly, one way or another it is better than nothing.");
            addItem(18, "Old leather armor", "the ties and plates are depleted, showing signs of repair and battle marks.");
            addItem(24, "Leather armor", "stronger than any other leather garment, better choice between speed and protection.");
            addItem(12, "Worn iron armor", "parts of the armor are worn out, cracks and traces of the previous owners are visible on the plates.");
            addItem(13, "Old iron armor", "squeaky, in some places traces of corrosion are visible.");
            addItem(10, "Iron armor", "forged from a solid material that glistens in the sun.");
            addItem(30, "Peasant clothes", "made of rough material but quite comfortable.");
            addItem(44, "Nobleman clothes", "embroidered from soft fabric and golden threads, looks catchy and luxurious.");
            addItem(10, "Mask", "suitable for a secular ball and for committing crimes.");
            addItem(12, "Mag's robe", "the threads and seams of this garment are saturated with magical energy.");
            addItem(13, "Archer's robe", "awakens suppressed magical powers in the wearer.");
            addItem(9, "Reinforced thief armor", "this armor balances strength and lightness.");
            addItem(11, "Knight armor", "heavy but very durable.");
            addItem(25, "Chain Shirt", "woven from small steel rings");
            addItem(26, "Bone armor", "harvested from the bones of previously living creatures.");
            addItem(12, "Assassin's silk cape", "designed for silent assassins who avoid direct confrontation.");
            addItem(17, "Light archer armor", "very agile, but has weak defense.");
            addItem(28, "Pilgrim's cloak", "protects from rain and prying eyes.");
        }

        private void addItem(int rare, String name, String description) {
            map.put(rare, new Pair(name, description));
        }

        private String getItem() {
            int roll = (int) (Math.random() * 100);
            return SearchNearItem(roll, map);
        }

        private String SearchNearItem(int roll, HashMap<Integer, Pair<String, String>> map) {
            Pair<String, String> item = new Pair<>("", "");
            while (roll <= 100) {
                Log.d("DebugLogs", "ItemGenerator: ClothData: roll: " + roll);
                if (map.get(roll) != null) {
                    item = map.get(roll);
                    Log.d("DebugLogs", "ItemGenerator: ClothData: item: " + item.first + "( " + item.second + " )");
                    return item.first + "( " + item.second + " )";
                } else {
                    roll++;
                }
            }
            Log.d("DebugLogs", "ItemGenerator: ClothData: item: none");
            return "";
        }
    }

    private static class WeaponData {
        private HashMap<Integer, Pair<String, String>> map;

        private WeaponData() {
            map = new HashMap<>();
            SetDefaultWeapons();
        }

        private void SetDefaultWeapons() {
            AddWeapon(30, "Saber", "chopping-cutting bladed edged weapon");
            AddWeapon(25, "Broadsword", "cutting-thrusting bladed edged weapon with a wide end");
            AddWeapon(26, "Poleax", "long-shaft ax with a crescent-shaped blade.");
            AddWeapon(50, "Halberd", "long-shaft ax. (sekira na deryvannom drevke)");
            AddWeapon(20, "One and a half hand sword", "Wide blade with a smooth taper to the point");
            AddWeapon(35, "Scythe", "a long metal blade, bent slightly inward, with a wooden handle attached to it near the base of the knife");
            AddWeapon(16, "Crossbow", "bow-shaped hand throwing weapons.");
            AddWeapon(70, "Bolt", "crossbow ammunition.");
            AddWeapon(14, "Dagger", "Stabbing double-edged weapon with a short blade.");
            AddWeapon(12, "Battle axe", "blade sharpened on one side on a wooden handle.");
            AddWeapon(46, "Mace", "impact-crushing weapon derived from a conventional wooden stick.");
            AddWeapon(71, "Wooden pole", "non-lethal weapon, needed to unbalance.");
            AddWeapon(18, "Bow and arrows", "throwing weapon designed for shooting arrows.");
            AddWeapon(15, "Shield", "personal protective equipment, a type of weapon designed to protect against cold hand and throwing weapons.");
            AddWeapon(14, "Throwing knives", "kind of knife designed for throwing.");
            AddWeapon(21, "Bolas", "hunting throwing weapons consisting of a belt or bundle of belts, to the ends of which round stones wrapped in leather, bone weights, stone balls are tied.");
            AddWeapon(25, "Spear", "throwing, stabbing or piercing-chopping pole-arm edged weapons.");
        }

        private void AddWeapon(int rare, String name, String description) {
            map.put(rare, new Pair(name, description));
        }

        private String getWeapon() {
            int roll = (int) (Math.random() * 100);
            return SearchNearWeapon(roll, map);
        }

        private String SearchNearWeapon(int roll, HashMap<Integer, Pair<String, String>> map) {
            Pair<String, String> item = new Pair<>("", "");
            boolean searching = true;
            while (roll <= 100) {
                Log.d("DebugLogs", "ItemGenerator: WeaponData: roll: " + roll);
                if (map.get(roll) != null) {
                    item = map.get(roll);
                    Log.d("DebugLogs", "ItemGenerator: WeaponData: item: " + item.first + "( " + item.second + " )");
                    return item.first + "( " + item.second + " )";
                } else {
                    roll++;
                }
            }
            Log.d("DebugLogs", "ItemGenerator: WeaponData: item: none");
            return "";
        }
    }

        private static class PotionData {
            private HashMap<Integer, Pair<String, String>> map;

            private PotionData() {
                map = new HashMap<>();
                SetDefaultPotions();
            }

            private void SetDefaultPotions() {
                AddPotion(50, "health potion", "This potion is filled with life essence collected from the pollen of beneficial plants.");
                AddPotion(40, "mana potion", "A vessel full of magical energy flowing through your veins.");
                AddPotion(35, "stamina potion", "bitter taste, but quickly puts you on your feet.");
                AddPotion(30, "potion of rage", "a mixture of bitter root and pungent spices, you will feel an unbridled rage that covers");
                AddPotion(25, "cold immunity potions", "for the duration of this potion, the element of cold will not harm you.");
                AddPotion(26, "fire immunity potions", "for the duration of this potion, the element of cold will not harm you.");
                AddPotion(32, "lightning resistance potion", "with this potion, electricity is no longer an obstacle for you.");
                AddPotion(20, "warrior potion", "it was created exclusively for warriors to raise their fighting spirit and strength.");
                AddPotion(21, "magician potions", "drinking it, magicians can cast stronger spells.");
                AddPotion(22, "archer's potion", "an archer who drinks this potion will never miss.");
                AddPotion(23, "thief's potion", "this potion makes the thief more agile and faster.");
                AddPotion(15, "invisibility potion", "invisible liquid inside the bottle will make you invisible too.");
                AddPotion(31, "cancellation potion", "it removes all negative and positive effects from you, and also removes toxins from your body.");
                AddPotion(51, "potion of eloquence", "smells like alcohol…");
                AddPotion(10, "revitalization potion", "being dying during the battle, you can drink this elixir and get a second chance.");
                AddPotion(19, "anti-magic poison", "takes away from the target the ability to magic.");
                AddPotion(24, "poison", "poisons target, dealing slow damage.");
                AddPotion(12, "paralysis poison", "immobilizes and paralyzes the target for a while.");
                AddPotion(24, "water breath potion", "after drinking this potion, you can breathe underwater for a while.");
                AddPotion(14, "potion of potency", "if you suddenly need a \"second sword\"");
            }

            private void AddPotion(int rare, String name, String description) {
                map.put(rare, new Pair(name, description));
            }

            private String getPotion() {
                int roll = (int) (Math.random() * 100);
                return SearchNearItem(roll, map);
            }

            private static String SearchNearItem(int roll, HashMap<Integer, Pair<String, String>> map) {
                Pair<String, String> item = new Pair<>("", "");
                boolean searching = true;
                while (roll <= 100) {
                    Log.d("DebugLogs", "ItemGenerator: PotionData: roll: " + roll);
                    if (map.get(roll) != null) {
                        item = map.get(roll);
                        Log.d("DebugLogs", "ItemGenerator: ClothData: item: " + item.first + "( " + item.second + " )");
                        return item.first + "( " + item.second + " )";
                    } else {
                        roll++;
                    }
                }
                Log.d("DebugLogs", "ItemGenerator: PotionData: item: none");
                return "";
            }
        }

        private static class RoleItemData {
            private ArrayList<HashMap<Integer, String>> list;
            private HashMap<Integer, String> knight;
            private HashMap<Integer, String> mag;
            private HashMap<Integer, String> thief;
            private HashMap<Integer, String> rower;
            private String role;

            private RoleItemData(String role) {
                list = new ArrayList<>();
                knight = new HashMap<>();
                mag = new HashMap<>();
                thief = new HashMap<>();
                rower = new HashMap<>();

                this.role = role;
                SetDefaultRoleItems();
            }

            private void SetDefaultRoleItems() {
                AddRoleItem(50, "armor", "Knight");
                AddRoleItem(51, "One and a half hand sword", "Knight");
                AddRoleItem(30, "Mace", "Knight");
                AddRoleItem(31, "Shield", "Knight");
                AddRoleItem(25, "Bow and arrows", "Knight");
                AddRoleItem(26, "Halberd", "Knight");
                AddRoleItem(20, "health potion", "Knight");
                AddRoleItem(21, "stamina potion", "Knight");
                AddRoleItem(22, "warrior potion", "Knight");
                AddRoleItem(52, "mage's robe", "Mag");
                AddRoleItem(40, "magic staff", "Mag");
                AddRoleItem(35, "mana potion", "Mag");
                AddRoleItem(27, "fire immunity potions", "Mag");
                AddRoleItem(28, "magician potions", "Mag");
                AddRoleItem(23, "Bolas", "Thief");
                AddRoleItem(32, "Throwing knives", "Thief");
                AddRoleItem(33, "Dagger", "Thief");
                AddRoleItem(34, "pilgrim's cloak", "Thief");
                AddRoleItem(41, "Worn Leather armor", "Thief");
                AddRoleItem(24, "health potion", "Thief");
                AddRoleItem(29, "stamina potion", "Thief");
                AddRoleItem(35, "potion of eloquence", "Thief");
                AddRoleItem(36, "thief's potion", "Thief");
                AddRoleItem(10, "invisibility potion", "Thief");
                AddRoleItem(42, "Crossbow", "Rower");
                AddRoleItem(70, "Bolt", "Rower");
                AddRoleItem(60, "Bow", "Rower");
                AddRoleItem(43, "Arrows", "Rower");
                AddRoleItem(35, "Archer armor", "Rower");
                AddRoleItem(71, "Worn jacket", "Rower");
                AddRoleItem(19, "Archer potion", "Rower");
                AddRoleItem(62, "Poison", "Rower");
            }

            private void AddRoleItem(int rare, String name, String role) {
                switch (role) {
                    case "Knight":
                        knight.put(rare, name);
                        break;
                    case "Mag":
                        mag.put(rare, name);
                        break;
                    case "Rower":
                        rower.put(rare, name);
                        break;
                    case "Thief":
                        thief.put(rare, name);
                        break;
                }
                UpdateData();
            }

            private void UpdateData() {
                list.clear();
                list.add(knight);
                list.add(mag);
                list.add(rower);
                list.add(thief);
            }

            private String getRoleItem() {
                String item;
                int roll = (int) (Math.random() * 100);
                switch (role) {
                    case "Knight":
                        item = SearchNearItem(roll, knight);
                        break;
                    case "Mag":
                        item = SearchNearItem(roll, mag);
                        break;
                    case "Rower":
                        item = SearchNearItem(roll, rower);
                        break;
                    case "Thief":
                        item = SearchNearItem(roll, thief);
                        break;
                    default:
                        return "";
                }
                return item;
            }

            private static String SearchNearItem(int roll, HashMap<Integer, String> map) {
                String item = "";
                boolean searching = true;
                while (roll <= 100) {
                    Log.d("DebugLogs", "ItemGenerator: RoleItemData: roll: " + roll);
                    if (map.get(roll) != null) {
                        item = map.get(roll);
                        Log.d("DebugLogs", "RoleItemData: RoleItemData: item: " + item);
                        return item;
                    } else {
                        roll++;
                    }
                }
                Log.d("DebugLogs", "RoleItemData: RoleItemData: item: none");
                return "";
            }
        }

        private static class Trash {
            private ArrayList<String> list;

            private Trash() {
                list = new ArrayList<>();
                SetDefaultTrash();
            }

            private void SetDefaultTrash() {
                AddTrash("glass bottle");
                AddTrash("nails");
                AddTrash("rope-ladder");
                AddTrash("boards");
                AddTrash("dishes");
                AddTrash("lamp");
                AddTrash("fork");
                AddTrash("tablecloth");
                AddTrash("table leg");
                AddTrash("dye");
                AddTrash("silk");
                AddTrash("the cloth");
                AddTrash("broom");
                AddTrash("Arrowhead");
                AddTrash("bowler hat");
                AddTrash("porcelain");
                AddTrash("vase");
                AddTrash("feathers");
                AddTrash("picture");
                AddTrash("brush");
                AddTrash("burnt book");
                AddTrash("tweezers");
                AddTrash("saw");
                AddTrash("tools");
                AddTrash("hook");
                AddTrash("log");
                AddTrash("doll");
                AddTrash("wooden sword");
                AddTrash("dummy");
                AddTrash("torn clothes");
                AddTrash("Teddy bear");
                AddTrash("wolf skin");
                AddTrash("bear skin");
                AddTrash("deer skin");
                AddTrash("medallion");
            }

            private void AddTrash(String name) {
                list.add(name);
            }

            private String getTrash() {
                int roll = (int) (Math.random() * list.size());
                return list.get(roll);
            }
        }
    }
