package com.example.leaf_disease_detection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Scanner;

public class Prediction_Results extends AppCompatActivity {

    TextView predicted_class, disease_info;
    Button search;
    String result;
    String pred_class;
    Float conf;
    String conf_perc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction_results);

        getSupportActionBar().setTitle("Predictions");

        predicted_class=findViewById(R.id.predclass);
        disease_info=findViewById(R.id.disease_info_tv);
        search=findViewById(R.id.Search_btn);

        Intent intent=getIntent();
        pred_class=intent.getStringExtra("Predicted_class");
        conf=intent.getFloatExtra("Confidence",0);
        conf_perc= String.format("%.0f",conf*100);

        String fin_res= pred_class + " : " + conf_perc + "%";
        predicted_class.setText(fin_res);

        if(pred_class.equals("Cordana_BananaLeaf_disease")){
            result="cordana leaf disease";
            String Fin_res= "Cordana Leaf Disease" + " : " + conf_perc + "%";
            predicted_class.setText(Fin_res);
//            String info= "Cordana leaf spot caused by the fungus Cordana musae, has been reported from a number of banana-producing areas throughout the world. Although widely distributed, it is not considered as being of much importance. Typical symptoms of this disease appear as oval, pale brown spots, and as long strips of light brown necrosed tissue sometimes extending from the leaf margins to the midrib. Large areas of the leaf may be destroyed. Diseased leaves may be quite conspicuous as the infected areas are usually surrounded by a bright yellow ororange zone.\n "+
//                    "\nControl:\n\n" + "To reduce disease levels, infected leaves should be removed. Monitor plants for symptoms and when required apply a registered fungicide.  ";
//            disease_info.setText(info);
            disease_info.setText(R.string.Cordana);

        }
        else if(pred_class.equals("Pestalotiopsis_BananaLeaf_Disease")){
            result="Pestalotiopsis leaf disease";
            String Fin_res= "Pestalotiopsis Leaf Disease" + " : " + conf_perc + "%";
            predicted_class.setText(Fin_res);
//            String info= "Pestalotiopsis is a fungus that causes diseases of both the palm leaf petiole and leaf blade, often at the same time. Palm leaf diseases caused by Pestalotia are the same as those caused by Pestalotiopsis." +
//                    "The fungus Pestalotiopsis causes leaf spots, petiole/rachis blights and sometimes a bud rot of palms. In other words, unlike the other leaf spot and petiole blight pathogens, which attack either the leaf blade or the leaf petiole, Pestalotiopsis attacks all parts of the leaf from base to tip. It is also one of the more ubiquitous fungi in the palm canopy, and is easily isolated from healthy palm tissue." +
//                    "\n\nSymptoms:\n\n"+"Disease development by Pestalotiopsis can be restricted to only the leaf blade (leaflets or leaf segments) or only the petiole and rachis; or, it can develop on both tissues at the same time (Figure 1). Spots will begin as very small yellow, brown or black spots." +
//                    "\n\nControl:\n\n Avoiding pests, diseases and weeds by good practice in cultivation methods, cultivar selection, garden hygiene and encouraging or introducing natural enemies, should be the first line of control. If chemical controls are used, they should be used only in a minimal and highly targeted manner.";
//            disease_info.setText(info);
            disease_info.setText(R.string.Pestalotiopsis);

        }
        else if(pred_class.equals("Sigatoka_BananaLeaf_Disease")){
            result="Sigatoka leaf disease";
            String Fin_res= "Sigatoka Leaf Disease" + " : " + conf_perc + "%";
            predicted_class.setText(Fin_res);
//            String info= "When spores of M. fijiensis are deposited on a susceptible banana leaf, they germinate within three hours if the humidity is high or a film of water is present. The optimal temperature for germination of the conidia is 27 °C (81 °F). The germ tube grows epiphytically over the epidermis for two to three days before penetrating the leaf through a stoma.Black Sigatoka is a leaf spot disease of banana. It is an important economic banana disease in many countries around the world. Severely infected leaves can die, significantly reducing fruit yield, and causing mixed and premature ripening of fruit bunches" +
//                    "\n\nSymptoms:\n\n" +
//                    "Black Sigatoka is also known as black leaf streak, causing streaks that run parallel to the leaves. It affects banana trees specifically in tropical climates; including Asia, West Africa, China, and South America. Black Sigatoka is a foliar disease of banana caused by the fungus Pseudocercospora fijiensis." +
//                    "\n\nControl\n\n There are several ways to control black Sigatoka, either by cultural and chemical means or by genetic engineering.One form of chemical control is preemptive use of fungicides on banana trees in order to protect them from primary inoculum.Leaves that have already been infected must be removed mechanically to save the rest of the tree.";
//            disease_info.setText(info);
            disease_info.setText(R.string.Sigatoka);

        }
        else if(pred_class.equals("Healthy _BananaLeaf")){
           result="Healthy Banana Leaf";
            String Fin_res= "Healthy Banana Leaf" + " : " + conf_perc + "%";
            predicted_class.setText(Fin_res);
//            String info= "Banana leaf has a rich source of polyphenols which may act against cancer cells. Fresh banana leaf juice may relieve people with psoriasis (itchy, scaly rashes on the skin). Regular banana leaf juice intake in the morning might reduce cough and cold. Banana leaves are large, flexible, and waterproof. They impart an aroma to food that is cooked in or served on them; steaming with banana leaves imparts a subtle sweet flavour and aroma to the dish. The leaves are not themselves eaten and are discarded after the contents are consumed.";
//            disease_info.setText(info);
            disease_info.setText(R.string.HealthyBanana);
        }

        else if(pred_class.equals("Anthracnose_PapayaLeaf_Disease")){
            result="Anthracnose";
            String Fin_res= "Anthracnose Leaf Disease" + " : " + conf_perc + "%";
            predicted_class.setText(Fin_res);
//            String info= "Anthracnose is a term used to loosely describe a group of related fungal diseases that typically cause dark lesions on leaves. In severe cases it may also cause sunken lesions and cankers on twigs and stems. Anthracnose affects many deciduous and evergreen trees and shrubs and can also infect vegetables, flowers, fruit, and turfgrass." +
//                    "\n\nSymptoms:\n\n Anthracnose symptoms vary by plant host and due to weather conditions. On landscape trees, the fungi infect developing shoots and expanding leaves. Small beige, brown, black, or black spots later appear on infected twigs of hosts such as elm, oak, and sycamore. Dead areas on leaves can be more irregular on hosts such as ash, maple, and willow, while sycamore and oak anthracnose lesions typically develop along major leaf veins.\n" +
//                    "\n\nControl:\n\n Avoid planting highly susceptible species including Modesto ash (Fraxinus velutina ‘Modesto’), American sycamore (Platanus occidentalis), and some cultivars of the London plane tree (Platanus x hispanica). California sycamore (Platanus racemosa) is very susceptible to anthracnose which disfigures the tree, giving it a twisted appearance. Pruning. Prune and destroy or bury infected leaves, twigs, and branches during fall or winter. Severe pruning of larger diameter branches is not a good practice for most trees, because it triggers bushy watersprouts, which are poorly attached to the trunk and are susceptible to diseases such as powdery mildew. Sanitation. Rake and dispose of fallen leaves and twigs during the growing season and in fall.";
//            disease_info.setText(info);
            disease_info.setText(R.string.Anthracnose);
        }

        else if(pred_class.equals("BacterialSpot_PapayaLeaf_Disease")){
            result="Bacterial Spot Leaf Disease";
            String Fin_res= "Bacterial Spot Leaf Disease" + " : " + conf_perc + "%";
            predicted_class.setText(Fin_res);
//            String info= "Bacterial spot is the most destructive leaf disease of capsicum. Black rot of brassica plants often follows infection by bacterial spot.\n" +
//                    "\n" +
//                    "\nCause\n" +
//                    "\nThe bacterium Xanthomonas campestris.\n" +
//                    "\n" +
//                    "\nSymptoms\n" +
//                    "\nThe first symptoms are usually small, yellow V-shaped areas developing along the leaf margin. These areas soon turn brown and dry out. Vein blackening may extend down the leaves into the petiole and the stem. When the stem is cut across, a black ring will be seen in the water-conducting tissues just below the bark.\n" +
//                    "\n" +
//                    "\nSoft, rotting organisms often quickly follow the invasion of fleshy leaves and petioles. Black rot bacterium then cause the plants to rot rapidly.";
//            disease_info.setText(info);
            disease_info.setText(R.string.Bacterial_spot);
        }

        else if(pred_class.equals("Curl_PapayaLeaf_Disease")){
            result="Curl Leaf Disease";
            String Fin_res= "Curl Leaf Disease" + " : " + conf_perc + "%";
            predicted_class.setText(Fin_res);
//            String info= "Peach leaf curl is a plant disease characterized by distortion and coloration of leaves and is caused by the fungus Taphrina deformans,[1] which infects peach, nectarine, and almond trees. T. deformans is found in the United States, Europe, Asia, Africa, Australia, and New Zealand.[2] Peach leaf curl reduces the amount of leaves and fruit produced by peach and nectarine trees.\n" +
//                    "\n\nSymptoms:\n" +
//                    "About two weeks after the leaves emerge, they develop yellow or reddish raised areas. They become distorted and puckered as they grow. The raised areas become red or purple. Leaves often fall off of the tree. The leaves that remain will develop a white powder on them. This powder is velvety spores of the fungus, ready to spread when water splashes on them." +
//                    "\n\nControl:\n The most effective method is to plant peach trees against a house wall under an overhanging roof, possibly covered by a mat during the winter, to keep winter rain from the buds before they burst (and incidentally to delay blossoming until spring frosts are over), until the temperature exceeds 16 °C (61 °F) in the spring, deactivating the fungus. Spraying the leaves with fungicides is the most common control method. The toxicity of these fungicides means they are not legally available to noncommercial growers in some countries. Spraying should be done in the winter well before budding. If trees are not sprayed early enough, treatment is ineffective.";
//            disease_info.setText(info);
            disease_info.setText(R.string.curl);
        }

        else if(pred_class.equals("RingSpot_PapayaLeaf_Disease")){
            result="Ring Spot Leaf Disease";
            String Fin_res= "Ring Spot Leaf Disease" + " : " + conf_perc + "%";
            predicted_class.setText(Fin_res);
//            String info= "Papaya ringspot disease disease causes distinctive symptoms on papaya (pawpaw or papaw) (Carica papaya) plants.Two papaya ringspot biosecurity containment zones legally prevent the spread of papaya ringspot disease out of South East Queensland.  Papaya ringspot is a destructive disease characterized by a yellowing and stunting of the crown of papaya trees, a mottling of the foliage  shoe-stringing of younger leaves, water-soaked streaking of the petioles (stalks), and small darkened rings on the surface of fruit " +
//                    "\n\nCause:\n" +
//                    "\nPapaya ringspot disease is caused by a plant virus called Papaya ringspot virus - type P (PRSV-P) which belongs to the genus Potyvirus in the family Potyviridae.\n" +
//                    "\n" +
//                    "\nTwo distinct types of Papaya ringspot virus are known:\n" +
//                    "\n" +
//                    "Papaya ringspot virus - type P (PRSV-P) which infects papaya and cucurbits\n" +
//                    "Papaya ringspot virus - type W (PRSV-W) which only infects cucurbit plants such as cucumber, melon, pumpkin, squash and zucchini." +
//                    "\n\nControl:\n\n In home gardens within the biosecurity zones, infected plants should be removed as soon as symptoms are noticed.";
//            disease_info.setText(info);
            disease_info.setText(R.string.ringspot);
        }

        else if(pred_class.equals("Healthy_PapayaLeaf")){
            result="Healthy Papaya Leaf";
            String Fin_res= "Healthy Papaya Leaf" + " : " + conf_perc + "%";
            predicted_class.setText(Fin_res);
//            String info= "Rich in Nutrients: Papaya leaves are a good source of vitamins A, C, and E, as well as minerals like calcium, phosphorus, and iron. Supports Immune System: Papaya leaves can boost immunity, helps with weight loss and improves gut health. Papaya leaves are commonly taken in extract, tea or juice form and have been found to treat symptoms associated with dengue fever. Other common uses include: reducing inflammation, improving blood sugar control, supporting skin and hair health, and preventing cancer.";
//            disease_info.setText(info);
            disease_info.setText(R.string.HealthyPapaya);
        }

//        else if(pred_class.equals("Banana-Cut Fruit")){
//            result="Anthracnose of banana";
//            String Fin_res= "Banana Cut Fruit Seed Disease" + " : " + conf_perc + "%";
//            predicted_class.setText(Fin_res);
////            String info="The blackening of seeds inside banana fruit is often associated with a disease called Anthracnose.\n This is a fruit disease caused by the fungus Collectotrichum musae. The disease is most common on cooking bananas and it often originates from field uninjured green fruits. Infection of the disease usually starts during the development of the fruit but remains quiescent until the fruit ripens, particularly during storage and marketing. Infected fruits first develop small black circular specks on the flowers, and on the skin and distal ends. Then the lesions increase in size and later become sunken and coalesce, forming large spots on the surface. As the fruit matures, typical lesions develop and this will continue on the mature fruit.";
////            disease_info.setText(info);
//            disease_info.setText(R.string.BananaCutFruit);
//        }

//        else if(pred_class.equals("Banana- Stem Panama Wilt")){
//            result="stem panama wilt disease of banana";
//            String Fin_res= "Banana Stem Panama Wilt" + " : " + conf_perc + "%";
//            predicted_class.setText(Fin_res);
////            String info= "Panama disease, a devastating disease of bananas caused by the soil-inhabiting fungus species Fusarium oxysporum forma specialis cubense. A form of fusarium wilt, Panama disease is widespread throughout the tropics and can be found wherever susceptible banana cultivars are grown. \n\n Symptom: \n\nThe first obvious signs of disease in most varieties are wilting and a light yellow colouring of the lower leaves, most prominent around the margins. They eventually turn a bright yellow colour with dead leaf  margins.\nSplitting of pseudostem base is a characteristic symptom.\n\nManagement:\n\nProper care should be given when planting susceptible cultivators such Rasthali, Monthan, Karpuravalli, Kadali, Pachanadan by selecting healthy suckers from disease fields\nRemove and destroy infested plant material after harvest.";
////            disease_info.setText(info);
//            disease_info.setText(R.string.BananaStemWilt);
//        }

//        else if(pred_class.equals("Papaya - Leaf Mosaic Virus")){
//            result="papaya leaf mosaic virus";
//            String Fin_res= "Papaya - Leaf Mosaic Virus" + " : " + conf_perc + "%";
//            predicted_class.setText(Fin_res);
////            String info= "The disease attacks the papaya plants of all age groups, but is most serious on young plants. The aphids are responsible for transmitting the disease. The disease symptoms appear on the top young leaves of the plants. The leaves are reduced in size and show blister like patches of dark-green tissue,alternating with yellowish-green lamina. The leaf petiole is reduced in length and the top leaves assume an upright position. The infected plants show a marked reduction in growth. The fruits borne on disease plants develop water soaked lesions with a central solid spot. Such fruits are elongated and reduced in size. \n\nControl :\n\n Good field sanitation such as removal and destruction of affected plant reduce the spread of the disease. Also, losses can be minimised controlling the population of aphid. Application of Carbofuran (1 kg a.i./ha) at the time of sowing seeds followed by 2-3 foliar sprays of Phosphamidon (0.05%) at an interval of 10 days starting from 15-20 days after sowing effectively checks the population of aphids. ";
////            disease_info.setText(info);
//            disease_info.setText(R.string.MosaiVirus);
//        }

//        else if(pred_class.equals("Papaya- Stem Foot Rot")){
//            result="Papaya Stem Foot Rot";
//            String Fin_res= "Papaya Stem Foot Rot" + " : " + conf_perc + "%";
//            predicted_class.setText(Fin_res);
////            String info= "Papaya foot rot, caused by a species of Phytophthtora, is a disease that is characterized by the appearance of spongy, water-soaked patches on the bark at the soil line. The patches enlarge rapidly and girdle the stem, causing the tissue to rot. The tissues become black, and the plant topples and dies. The disease continues to spread in the roots and destroys them. If the bark is opened, the internal tissues appear dry and honey-combed. \n\n Control\n\n The disease can be avoided if plants are grown in well-drained soil. Affected plants should be carefully dug up and destroyed by burning. Replanting should not be done in the same pit where disease has once appeared. When trees are weeded, care should be taken so that no injury is caused to the base of the stem.";
////            disease_info.setText(info);
//            disease_info.setText(R.string.StemFootRot);
//        }


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https:/www.google.com/search?q="+result)));
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this, disease_detection.class);
        startActivity(intent);
        finish();
    }
}