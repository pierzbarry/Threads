package com.example.demo.business.services;

import com.example.demo.business.entities.*;
import com.example.demo.business.entities.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ClimateRepository climateRepository;

    @Autowired
    OccasionRepository occasionRepository;

    @Autowired
    WindRepository windRepository;

    @Autowired
    UserService userService;

    @Value("${run.dataloader}")
    private boolean rundataloader;

    @Override
    public void run(String... args) throws Exception {

        if (rundataloader) {
            //if you want to run dataloader once then change run.dataloader = true in application.properties.
            // it will help you to not comment out whole class

            //Role
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));

            Role userRole = roleRepository.findByRole("USER");
            Role adminRole = roleRepository.findByRole("ADMIN");

            //User
            User admin = new User("admin@admin.com", userService.encode("password"), "Admin", "User", "admin");
            userService.saveAdmin(admin);

            User moe = new User("moe@gmail.com", userService.encode("password"), "Moe", "Boy", "moe");
            userService.saveUser(moe);

            //Category
            categoryRepository.save(new Category("top"));
            Category top = categoryRepository.findByName("top");

            categoryRepository.save(new Category("jacket"));
            Category jacket = categoryRepository.findByName("jacket");

            categoryRepository.save(new Category("bottom"));
            Category bottom = categoryRepository.findByName("bottom");

            categoryRepository.save(new Category("shoes"));
            Category shoe = categoryRepository.findByName("shoes");

            categoryRepository.save(new Category("accessories"));
            Category accessories = categoryRepository.findByName("accessories");

            //Climate
            climateRepository.save(new Climate("cold"));
            Climate cold = climateRepository.findByName("cold");

            climateRepository.save(new Climate("moderate"));
            Climate mild = climateRepository.findByName("moderate");

            climateRepository.save(new Climate("hot"));
            Climate hot = climateRepository.findByName("hot");

            climateRepository.save(new Climate("rainy"));
            Climate rainy = climateRepository.findByName("rainy");

            //Occasion
            occasionRepository.save(new Occasion("active"));
            Occasion active = occasionRepository.findByName("active");

            occasionRepository.save(new Occasion("party"));
            Occasion party = occasionRepository.findByName("party");

            occasionRepository.save(new Occasion("casual"));
            Occasion casual = occasionRepository.findByName("casual");

            occasionRepository.save(new Occasion("formal"));
            Occasion formal = occasionRepository.findByName("formal");


            //Wind
            windRepository.save(new Wind("light"));
            Wind light = windRepository.findByName("light");

            windRepository.save(new Wind("moderate"));
            Wind moderate = windRepository.findByName("moderate");

            windRepository.save(new Wind("high"));
            Wind high = windRepository.findByName("high");

            //Items

            itemRepository.save(new Item("cargo pants",
                    "khaki",
                    "polyester",
                    "medium",
                    "long",
                    "https://res.cloudinary.com/toyefule/image/upload/c_fill,g_face,h_150,r_50,w_150/v1553802838/Outfit/Cargo_khaki_1.jpg",
                    "men's wear",
                    moe,
                    bottom,
                    cold,
                    casual, moderate));
            Item pant = itemRepository.findByName("cargo pant");


            itemRepository.save(new Item("Wide-leg Suit Pants",
                    "red",
                    "Polyester",
                    "small",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576527098/outfit/hmgoepprod_1_wyn5ck.jpg",
                    "Woman's wear",
                    admin,
                    bottom,
                    mild,
                    casual,
                    light));
            Item wideLegSuitPants = itemRepository.findByName("Wide-leg Suit Pants");

            itemRepository.save(new Item("Dress Pants",
                    "black",
                    "Polyester",
                    "medium",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576529466/outfit/hmgoepprod_5_vj6on5.jpg",
                    "Woman's wear",
                    admin,
                    bottom,
                    cold,
                    formal,
                    moderate));
            Item dressPants = itemRepository.findByName("Dress Pants");



            itemRepository.save(new Item("Slacks",
                    "beige",
                    "cotton",
                    "small",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576527973/outfit/hmgoepprod_4_rwc5qo.jpg",
                    "Woman's wear",
                    admin,
                    bottom,
                    mild,
                    formal,
                    light));
            Item Slacks = itemRepository.findByName("Slacks");

            itemRepository.save(new Item("Joggers",
                    "Dark beige",
                    "cotton",
                    "small",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576531447/outfit/hmgoepprod_6_lgn3zz.jpg",
                    "Woman's wear",
                    admin,
                    bottom,
                    rainy,
                    casual,
                    moderate));
            Item Joggers = itemRepository.findByName("Joggers");

            itemRepository.save(new Item("Super Slim-fit Pants",
                    "black",
                    "cotton",
                    "small",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576598932/outfit/hmgoepprod_24_uxglvw.jpg",
                    "Woman's wear",
                    admin,
                    bottom,
                    rainy,
                    casual,
                    moderate));
            Item superSlimFitPants = itemRepository.findByName("Super Slim-fit Pants");

            itemRepository.save(new Item("Joggers High Waist",
                    "Green",
                    "cotton",
                    "small",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576531898/outfit/hmgoepprod_8_ay48t3.jpg",
                    "Woman's wear",
                    admin,
                    bottom,
                    cold,
                    active,
                    high));
            Item joggersHighWaist = itemRepository.findByName("Joggers High Waist");

            itemRepository.save(new Item("Pink Sports Pants",
                    "pink",
                    "cotton",
                    "small",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576599489/outfit/hmgoepprod_26_jh7mjk.jpg",
                    "Woman's wear",
                    admin,
                    bottom,
                    cold,
                    active,
                    high));
            Item pinkSportsPants = itemRepository.findByName("Pink Sports Pants");

            itemRepository.save(new Item("Slim High Ankle Jeans",
                    "blue",
                    "denim",
                    "small",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576532197/outfit/hmgoepprod_9_gcofhk.jpg",
                    "Woman's wear",
                    admin,
                    bottom,
                    mild,
                    casual,
                    moderate));
            Item slimHighAnkleJeans = itemRepository.findByName("Slim High Ankle Jeans");

            itemRepository.save(new Item("Skinny Biker Jeans",
                    "blue",
                    "denim",
                    "small",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576600461/outfit/hmgoepprod_27_ndtemi.jpg",
                    "Men's wear",
                    admin,
                    bottom,
                    mild,
                    casual,
                    moderate));
            Item skinnyBikerJeans = itemRepository.findByName("Skinny Biker Jeans");

            itemRepository.save(new Item("Pleated Skirt",
                    "pink",
                    "Polyester",
                    "small",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576553477/outfit/hmgoepprod_10_ysvr44.jpg",
                    "Woman's wear",
                    admin,
                    bottom,
                    hot,
                    formal,
                    light));
            Item pleatedSkirt = itemRepository.findByName("Pleated Skirt");

            itemRepository.save(new Item("Knee-length Skirt",
                    "black",
                    "Polyester",
                    "small",
                    "short",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576554257/outfit/hmgoepprod_11_nfd5do.jpg",
                    "Woman's wear",
                    admin,
                    bottom,
                    hot,
                    casual,
                    light));
            Item kneeSkirt = itemRepository.findByName("Knee-length Skirt");

            itemRepository.save(new Item("Sports Pants",
                    "black",
                    "Polyester",
                    "small",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576599135/outfit/hmgoepprod_25_tme8p3.jpg",
                    "Woman's wear",
                    admin,
                    bottom,
                    cold,
                    active,
                    high));
            Item sportsPants = itemRepository.findByName("Sports Pants");

            itemRepository.save(new Item("denim Skirt",
                    "blue",
                    "denim",
                    "small",
                    "short",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576554490/outfit/hmgoepprod_12_plkfzo.jpg",
                    "Woman's wear",
                    admin,
                    bottom,
                    hot,
                    casual,
                    light));
            Item denimSkirt = itemRepository.findByName("denim Skirt");

            itemRepository.save(new Item("Crêped Shorts",
                    "red",
                    "Polyester",
                    "small",
                    "short",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576555080/outfit/hmgoepprod_13_tlxpeo.jpg",
                    "Woman's wear",
                    admin,
                    bottom,
                    hot,
                    casual,
                    light));
            Item crêpedShorts = itemRepository.findByName("Crêped Shorts");

            itemRepository.save(new Item("High heel",
                    "black",
                    "Leather",
                    "7",
                    "short",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576597900/outfit/hmgoepprod_14_kd4zbq.jpg",
                    "Woman's wear",
                    admin,
                    shoe,
                    hot,
                    formal,
                    light));
            Item highHeel = itemRepository.findByName("High heel");

            itemRepository.save(new Item("Low heel boots",
                    "beige",
                    "Polyester",
                    "7",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576593882/outfit/hmgoepprod_15_inpf6g.jpg",
                    "Woman's wear",
                    admin,
                    shoe,
                    cold,
                    casual,
                    high));
            Item lowHeelBoots = itemRepository.findByName("Low heel boots");

            itemRepository.save(new Item("Flip flops",
                    "pink",
                    "Polyester",
                    "7",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576594107/outfit/hmgoepprod_16_mbs3w6.jpg",
                    "Woman's wear",
                    admin,
                    shoe,
                    hot,
                    casual,
                    light));
            Item flipFlops = itemRepository.findByName("Flip flops");



            itemRepository.save(new Item("No heel flat",
                    "white",
                    "Polyester",
                    "7",
                    "short",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576595643/outfit/hmgoepprod_17_dwb8bt.jpg",
                    "Woman's wear",
                    admin,
                    shoe,
                    hot,
                    casual,
                    light));
            Item noHeelFlat = itemRepository.findByName("No heel flat");

            itemRepository.save(new Item("Sneakers",
                    "pink",
                    "Polyester",
                    "7",
                    "short",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576595772/outfit/hmgoepprod_18_vmica9.jpg",
                    "Woman's wear",
                    admin,
                    shoe,
                    cold,
                    active,
                    moderate));
            Item sneakers = itemRepository.findByName("Sneakers");


            itemRepository.save(new Item("black sneakers",
                    "black",
                    "leather",
                    "7",
                    "short",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576595917/outfit/hmgoepprod_19_zabjm3.jpg",
                    "Woman's wear",
                    admin,
                    shoe,
                    cold,
                    active,
                    moderate));
            Item blackSneakers = itemRepository.findByName("black sneakers");

            itemRepository.save(new Item("men boot",
                    "dark brown",
                    "leather",
                    "9",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576596496/outfit/hmgoepprod_20_afobgz.jpg",
                    "men's wear",
                    admin,
                    shoe,
                    cold,
                    active,
                    high));
            Item menBoot = itemRepository.findByName("men boot");



            itemRepository.save(new Item("Sandals with Ties",
                    "dark brown",
                    "Polyester",
                    "7",
                    "short",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576596958/outfit/hmgoepprod_21_miy6uw.jpg",
                    "women's wear",
                    admin,
                    shoe,
                    hot,
                    formal,
                    light));
            Item sandalsWithTies = itemRepository.findByName("Sandals with Ties");

            itemRepository.save(new Item("Suede Pumps",
                    "red",
                    "Polyester",
                    "7",
                    "short",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576597418/outfit/hmgoepprod_22_q5eefp.jpg",
                    "women's wear",
                    admin,
                    shoe,
                    hot,
                    formal,
                    light));
            Item suedePumps = itemRepository.findByName("Suede Pumps");

            itemRepository.save(new Item("Sandals with Lacing",
                    "black",
                    "Polyester",
                    "7",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576598083/outfit/hmgoepprod_23_sqsuja.jpg",
                    "women's wear",
                    admin,
                    shoe,
                    hot,
                    formal,
                    light));
            Item sandalsWithLacing = itemRepository.findByName("Sandals with Lacing");

            itemRepository.save(new Item("Rain Boots",
                    "black",
                    "Polyester",
                    "7",
                    "long",
                    "https://res.cloudinary.com/deymfeqlp/image/upload/v1576600106/outfit/296495_1_41_mxn1sh.jpg",
                    "women's wear",
                    admin,
                    shoe,
                    rainy,
                    casual,
                    high));
            Item rainBoots = itemRepository.findByName("Rain Boots");

            itemRepository.save(new Item("printed t shirt",
                    "white",
                    "cotton",
                    "small",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524850/white_printed_t_shirt_hgyeco.jpg",
                    "women's wear",
                    admin,
                    top,
                    cold,
                    casual,
                    light));
            Item printedshirt = itemRepository.findByName("printed t shirt");

            itemRepository.save(new Item("velour one shoulder top",
                    "black",
                    "velour",
                    "small",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524850/Velous_one_should_top_cnolkz.jpg",
                    "women's wear",
                    moe,
                    top,
                    cold,
                    party,
                    light));
            Item shouldertop = itemRepository.findByName("shoulder top");

            itemRepository.save(new Item("short jersey top",
                    "black",
                    "silk",
                    "small",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524850/short_jersey_top_abu9vv.jpg",
                    "women's wear",
                    admin,
                    top,
                    cold,
                    casual,
                    light));
            Item jerseytop = itemRepository.findByName("short jersey top");

            itemRepository.save(new Item("lace top",
                    "red",
                    "satin",
                    "large",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524850/satin_top_with_lace_c4n0um.jpg",
                    "women's wear",
                    moe,
                    top,
                    hot,
                    party,
                    light));
            Item lacetop = itemRepository.findByName("lace top");

            itemRepository.save(new Item("lace bodysuit",
                    "black",
                    "mesh",
                    "medium",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524850/lace_and_mesh_bodysuit_n1qqfn.jpg",
                    "women's wear",
                    admin,
                    top,
                    hot,
                    casual,
                    light));
            Item bodysuit = itemRepository.findByName("lace bodysuit");

            itemRepository.save(new Item("jersey t shirt",
                    "silver",
                    "silk",
                    "medium",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524850/jersey_t_shirt_vuzlkz.jpg",
                    "women's wear",
                    moe,
                    top,
                    hot,
                    party,
                    moderate));
            Item jerseyshirt = itemRepository.findByName("jersey t shirt");

            itemRepository.save(new Item("khaki green shirt",
                    "green",
                    "khaki",
                    "medium",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524850/khaki_green_cotton_t_shirt_vazuke.jpg",
                    "women's wear",
                    moe,
                    top,
                    hot,
                    casual,
                    light));
            Item khakishirt = itemRepository.findByName("khaki green shirt");

            itemRepository.save(new Item("pink jersey shirt",
                    "pink",
                    "cotton",
                    "medium",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524850/jersey_t_shirt_pink_udxgmy.jpg",
                    "women's wear",
                    admin,
                    top,
                    hot,
                    casual,
                    light));
            Item pinkjersey = itemRepository.findByName("pink jersey shirt");

            itemRepository.save(new Item("modal polka dot",
                    "modal",
                    "polyester",
                    "large",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524850/modal_polka_dot_turtleneck_long_sleeve_top_ox3fyr.jpg",
                    "women's wear",
                    moe,
                    top,
                    cold,
                    formal,
                    moderate));
            Item modalpolka = itemRepository.findByName("modal polka shirt");

            itemRepository.save(new Item("friends printed shirt",
                    "black",
                    "cotton",
                    "extra small",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524849/black_friends_t_shirt_zkesgs.jpg",
                    "women's wear",
                    admin,
                    top,
                    rainy,
                    casual,
                    light));
            Item friendsshirt = itemRepository.findByName("friends printed shirt");

            itemRepository.save(new Item("sequined bralette",
                    "silver",
                    "satin",
                    "small",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524849/bralettewsequins_ugsegd.jpg",
                    "women's wear",
                    moe,
                    top,
                    hot,
                    party,
                    light));
            Item bralette = itemRepository.findByName("sequined bralette");

            itemRepository.save(new Item("black printed shirt",
                    "black",
                    "polyester",
                    "large",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524849/black_printed_t_shirt_qjoit3.jpg",
                    "women's wear",
                    admin,
                    top,
                    rainy,
                    casual,
                    light));
            Item blackshirt = itemRepository.findByName("black printed shirt");

            itemRepository.save(new Item("faux fur top",
                    "white",
                    "fur",
                    "medium",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524849/fauxfurtop_ttcodi.jpg",
                    "women's wear",
                    moe,
                    top,
                    cold,
                    formal,
                    moderate));
            Item fauxfurtop = itemRepository.findByName("faux fur top");

            itemRepository.save(new Item("beige sweater",
                    "beige",
                    "wool",
                    "extra large",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524849/beige_sweater_with_beads_krbnbc.jpg",
                    "women's wear",
                    admin,
                    top,
                    cold,
                    active,
                    high));
            Item sweater = itemRepository.findByName("beige sweater");

            itemRepository.save(new Item("puff sleeves",
                    "black",
                    "wool",
                    "medium",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524849/black_puff_sleeved_top_y6tkp8.jpg",
                    "women's wear",
                    moe,
                    jacket,
                    cold,
                    formal,
                    moderate));
            Item puffsleeve = itemRepository.findByName("puff sleeves");

            itemRepository.save(new Item("pink polka top",
                    "pink",
                    "silk",
                    "small",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576524849/black_polka_dots_puffed_sleeved_top_pbaxti.jpg",
                    "women's wear",
                    admin,
                    top,
                    cold,
                    formal,
                    light));
            Item pinkpolkatop = itemRepository.findByName("pink polka top");

            itemRepository.save(new Item("red slacks",
                    "red",
                    "polyester",
                    "small",
                    "long",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576531962/hmgoepprod_wlwhaz.jpg",
                    "women's wear",
                    moe,
                    bottom,
                    cold,
                    casual,
                    light));
            Item redslacks = itemRepository.findByName("red slacks");

            itemRepository.save(new Item("puffer jacket",
                    "pink",
                    "satin",
                    "small",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576532940/puffer_jacket_wx7epv.jpg",
                    "women's wear",
                    admin,
                    top,
                    cold,
                    casual,
                    light));
            Item pufferjacket = itemRepository.findByName("puffer jacket");

            itemRepository.save(new Item("beige wool coat",
                    "beige",
                    "wool",
                    "small",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576532938/beige_wool_coat_xonzrh.jpg",
                    "women's wear",
                    moe,
                    jacket,
                    cold,
                    casual,
                    high));
            Item woolcoat = itemRepository.findByName("beige wool coat");

            itemRepository.save(new Item("down jacket",
                    "black",
                    "satin",
                    "extra large",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576532937/down_jacket_r5cqro.jpg",
                    "men's wear",
                    admin,
                    jacket,
                    cold,
                    casual,
                    high));
            Item downjacket = itemRepository.findByName("down jacket");

            itemRepository.save(new Item("sequin jacket",
                    "silver",
                    "sequins",
                    "medium",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576532936/sequin_jacket_hjkkkd.jpg",
                    "women's wear",
                    moe,
                    jacket,
                    hot,
                    casual,
                    light));
            Item sequinedjacket = itemRepository.findByName("sequin jacket");

            itemRepository.save(new Item("faux parka",
                    "black and brown",
                    "cotton",
                    "small",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576532935/fauz_parka_bo1bfv.jpg",
                    "men's wear",
                    admin,
                    jacket,
                    cold,
                    casual,
                    high));
            Item fauxparka = itemRepository.findByName("faux parka");

            itemRepository.save(new Item("padded parka",
                    "blue",
                    "cotton",
                    "large",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576532934/padded_parka_dzbspj.jpg",
                    "men's wear",
                    moe,
                    jacket,
                    cold,
                    casual,
                    high));
            Item paddedparka = itemRepository.findByName("padded parka");

            itemRepository.save(new Item("knit wool blend hat",
                    "brown",
                    "wool",
                    "small",
                    "short",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576593795/knit_wool_blend_hat_o96mut.jpg",
                    "women's wear",
                    admin,
                    accessories,
                    cold,
                    casual,
                    light));
            Item woolhat = itemRepository.findByName("knit wool blend hat");

            itemRepository.save(new Item("alpaca blend scarf",
                    "white and pink",
                    "alpaca",
                    "small",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576593795/alpaca_blend_scarf_f4g6o0.jpg",
                    "women's wear",
                    moe,
                    accessories,
                    cold,
                    casual,
                    light));
            Item alpacascarf = itemRepository.findByName("alpaca blend scarf");

            itemRepository.save(new Item("shoulder purse",
                    "black",
                    "synthetic",
                    "small",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576593796/bag_rp02xy.jpg",
                    "women's wear",
                    admin,
                    accessories,
                    rainy,
                    casual,
                    light));
            Item shoulderpurse = itemRepository.findByName("shoulder purse");

            itemRepository.save(new Item("6pc ear cuffs",
                    "multicolored",
                    "plastic",
                    "small",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576593795/6pc_ear_cuffs_ispehc.jpg",
                    "women's wear",
                    moe,
                    accessories,
                    rainy,
                    casual,
                    moderate));
            Item earcuffs = itemRepository.findByName("6pc ear cuffs");

            itemRepository.save(new Item("cashmere travel pack",
                    "cream",
                    "cashmere",
                    "medium",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576593795/cashmere_travel_pack_yj0bw3.jpg",
                    "men's wear",
                    admin,
                    accessories,
                    cold,
                    formal,
                    high));
            Item cashmerepack = itemRepository.findByName("cashmere travel pack");

            itemRepository.save(new Item("narrow belt",
                    "black",
                    "leather",
                    "medium",
                    "long",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576593796/narrow_belt_fa7yek.jpg",
                    "men's wear",
                    admin,
                    accessories,
                    mild,
                    casual,
                    light));
            Item narrowbelt = itemRepository.findByName("narrow belt");

            itemRepository.save(new Item("dark yellow leather gloves",
                    "dark yellow",
                    "leather",
                    "extra small",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576593795/dyellow_leather_gloves_vvyfi3.jpg",
                    "Unisex",
                    moe,
                    accessories,
                    cold,
                    casual,
                    high));
            Item dyellowleathergloves = itemRepository.findByName("dark yellow leather gloves");

            itemRepository.save(new Item("scrunchie with scarf",
                    "black and white",
                    "satin",
                    "small",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576593795/scrunchie_with_scarf_wrkzyv.jpg",
                    "women's wear",
                    admin,
                    accessories,
                    cold,
                    casual,
                    light));
            Item scrunchiescarf = itemRepository.findByName("scrunchie with scarf");

            itemRepository.save(new Item("leather gloves with tassels",
                    "black",
                    "leather",
                    "medium",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576593795/leather_gloves_with_tassels_bccuo7.jpg",
                    "Unisex",
                    moe,
                    accessories,
                    cold,
                    formal,
                    light));
            Item tasselgloves = itemRepository.findByName("leather gloves with tassels");

            itemRepository.save(new Item("double stranded necklace",
                    "pink",
                    "plastic",
                    "medium",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576593795/double_strand_necklace_zzjd7i.jpg",
                    "women's wear",
                    admin,
                    accessories,
                    rainy,
                    formal,
                    light));
            Item necklace = itemRepository.findByName("double stranded necklace");

            itemRepository.save(new Item("felted wool hat",
                    "brown",
                    "felted wool",
                    "large",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576593795/felted_wool_hat_gngyku.jpg",
                    "Unisex",
                    admin,
                    accessories,
                    rainy,
                    formal,
                    light));
            Item feltedwoolhat = itemRepository.findByName("felted wool hat");

            itemRepository.save(new Item("rib knit hat",
                    "blue",
                    "beanie",
                    "medium",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576593795/rib_knit_hat_avts9r.jpg",
                    "men's wear",
                    admin,
                    accessories,
                    rainy,
                    casual,
                    light));
            Item knithat = itemRepository.findByName("rib knit hat");

            itemRepository.save(new Item("leather bag",
                    "brown",
                    "leather",
                    "large",
                    "short",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576598449/leather_bag_fec4fc.jpg",
                    "men's wear",
                    admin,
                    accessories,
                    cold,
                    casual,
                    light));
            Item leatherbag = itemRepository.findByName("leather bag");

            itemRepository.save(new Item("mens leather watch",
                    "red",
                    "leather",
                    "medium",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576598449/leather_watch_yngf6n.jpg",
                    "men's wear",
                    admin,
                    accessories,
                    hot,
                    formal,
                    light));
            Item menswatch = itemRepository.findByName("mens leather watch");

            itemRepository.save(new Item("bow tie",
                    "black",
                    "satin",
                    "small",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576598449/satin_bow_tie_ieozgr.jpg",
                    "men's wear",
                    admin,
                    accessories,
                    hot,
                    formal,
                    high));
            Item bowtie = itemRepository.findByName("bow tie");

            itemRepository.save(new Item("clear eyeglasses",
                    "clear",
                    "glass",
                    "small",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576598449/clear_eyeglasses_zi7fqo.jpg",
                    "unisex",
                    admin,
                    accessories,
                    rainy,
                    casual,
                    light));
            Item eyeglasses = itemRepository.findByName("clear eyeglasses");

            itemRepository.save(new Item("black leather wallet",
                    "black",
                    "leather",
                    "medium",
                    "long sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576598449/black_leather_wallet_tqjevi.jpg",
                    "men's wear",
                    admin,
                    accessories,
                    hot,
                    formal,
                    moderate));
            Item wallet = itemRepository.findByName("black leather wallet");

            itemRepository.save(new Item("hat with earflaps",
                    "blue",
                    "beanie",
                    "small",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576598449/hat_with_earflaps_koejag.jpg",
                    "womens wear",
                    admin,
                    accessories,
                    rainy,
                    casual,
                    light));
            Item earflapshat = itemRepository.findByName("hat with earflaps");

            itemRepository.save(new Item("black leather watch",
                    "black",
                    "leather",
                    "medium",
                    "short sleeve",
                    "https://res.cloudinary.com/dxh39s0nz/image/upload/v1576598449/black_leather_watch_kirhrf.jpg",
                    "men's wear",
                    admin,
                    accessories,
                    rainy,
                    formal,
                    light));
            Item leatherwatch = itemRepository.findByName("black leather watch");
        }
    }
}
