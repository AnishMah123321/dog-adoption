package com.codefororlando.fyp.shelter;

import android.os.Bundle;
import android.util.Log;

import com.codefororlando.fyp.R;
import com.codefororlando.fyp.adapter.DogShelterAdapter;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DogShelterActivity extends AppCompatActivity {
    private static final String TAG = "DogShelterActivity";
    RecyclerView recyclerView;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
   private ArrayList<String> mDogShelter = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog_shelter_recycler);

        initImages();
        shelterDescription();

        recyclerView =  findViewById(R.id.dogShelterRecycler);



    }

    private void shelterDescription() {
        mDogShelter.add("We are their voice\n" +
                "Sneha’s Care is one of the largest animal welfare charities of Nepal. Established in 2014 by animal advocate Ms. Sneha Shrestha to protect the street and community dogs from torture, cruelty or ill usage of any kind, it has long campaigned to develop the welfare of man’s best friend. We at Sneha’s Care provide professional medical and humane care to injured, sick and abandoned dogs and other street animals in its animal shelter and also in the field. We believe that in the actions of individuals, businesses, and governments, all forms of animal exploitation including factory farming should not be acted. Sneha’s Care is continuously campaigning and attempting to introduce animal welfare law in Nepal for improved safeguards for animals and penalties for animal abuse and cruelty. We are also actively spreading animal welfare awareness on a national scale and educating the public to be compassionate to all nonhumans.\n" +
                "The Dogs of Kathmandu are often subjected to the most horrific cruelty. They are beaten, kicked and hit-and-run by vehicle most of the time making them injured, in pain or starving. Sneha’s Care receives almost 80 to 100 animal cruelty and abuse cases mostly related to street dogs by hotline, email and social media and we handle almost all of the cases depending on the resource availability. We have a well-equipped team consisting of veterinary doctors, assistants, and helpers who handle the cases by visiting the spot where the incident takes place. Our team is always-on-the-move in our animal ambulance where the full-fledged medical facility has been installed to transfer the animals to our clinic during serious cases. We try to handle almost all the received cases by our means and sometimes locals, and other volunteers also support in rescue and medication. After analyzing the spot animal situation, our team decides what should be done and where? In most cases where the injuries are minor, we medicate the animals in the spot and release in the same spot, but in serious cases, we transfer it to our clinic.");

        mDogShelter.add("The Kathmandu Animal Treatment Centre (KAT Centre) was first envisioned after Jan Salter, the founder of KAT, visited the organization “Help in Suffering” in Jaipur, India.\n" +
                "\n" +
                "Impressed with the way this and other animal welfare organizations have managed difficult street dog problems and eliminated rabies through Animal Birth Control (ABC) and widespread rabies vaccinations, Jan came back to Nepal convinced that Kathmandu, Nepal could also become a rabies-free, dog-friendly city.\n" +
                "\n" +
                "The KAT Centre was registered as a non-profit, charitable animal welfare organisation in 2003. KAT officially opened its doors on 9 May 2004 and it has grown steadily since then. As we have grown, our location has changed several times, but the founding principals and our work remain the same:\n" +
                "\n" +
                "To rescue and treat sick and injured animals living on the streets of Kathmandu.\n" +
                "To lower the street dog (and cat) population by spaying and neutering.\n" +
                "To reduce the incidence of rabies and other viruses through vaccination and education.\n" +
                "To promote animal welfare in the widest sense of the term.\n" +
                "Our site is based on its own land, giving us security for the future and the freedom to continue expanding our facilities when the opportunity arises.\n" +
                "\n" +
                "We are constantly looking ahead and developing our long term strategy for the continued management and protection of the street animals of Kathmandu.\n");

        mDogShelter.add("Founder of Animal Rescue Nepal, Thakuri has almost saved more than three thousand dogs. She has now over 200 dogs in her shelter. As other people across Nepal worship dogs on annual as a ritual, rescuing and saving the helpless stray dogs is her daily ritual. This is based on Shrijana Thakuri’s posts.Her loves and dedication to love innocent animal is exemplary.\n" +
                "Of the 200 dogs living in her shelter, most that have been abused, abandoned or are suffering from diseases. Some are missing an eye, others don’t have legs. But without any hesitation, Thakuri looks after them all.Many would think that the dogs in the shelter would be limited to local breeds. But, the shelter is also home to exotic breeds such as Labradors and Japanese Spitzs, abandoned by their owners. She also looks after dogs involved in hit-and-run cases.\n" +
                "aking care of over 200 dogs isn’t easy. She adds that this journey hasn’t been easy because it is evident that this isn’t everyone’s cup of tea. “No one does this because this requires sacrifice. To give up a family and take care of these little dogs isn’t easy.”\n" +
                "\n" +
                "Thakuri shares that the average monthly cost for medical treatment, food and medicines can reach as high as Rs. 300,000. “The medicines for dogs are as expensive as the ones for humans and the vet bills are expensive. At an average, we use one sack of rice a day to feed everyone. The occasional meat and egg make it quite hard to balance books.”");

        mDogShelter.add("Community Dog Welfare Kopan, a non-profit organization dedicated to developing community awareness and responsibility for dog welfare near Kopan Monastery on the outskirts of Kathmandu, Nepal.\n" +
                "\n" +
                "Community Dog Welfare evolved from caring for dogs picked up on the street and brought home for veterinary treatment and recovery.  With local and international support we have created an expanding network for dog welfare through practical assistance, low cost treatment, dog health programmes and creating awareness, all of which have received a positive response in the local community.  Care is provided for sick, injured, aged and abandoned dogs in our rescue centre.\n" +
                "\n" +
                "Because EVERY DOG’S LIFE MATTERS, the overall aim is to give EVERY DOG A CHANCE FOR A BETTER LIFE.  We care for vulnerable dogs, and promote dog welfare by encouraging local people to care for their dogs, in order to develop a healthier, safer community environment.  Community Dog Welfare is systematically extending its coverage through annual programmes for dog health, neutering and rabies control.\n" +
                "\n" +
                "International volunteers are welcome; working at our centre gives them the opportunity to make a difference in the community, both for the welfare of people and dogs.  We are affiliated with the Himalayan Animal Treatment Centre (HAT-UK) and Himalayan Animal Rescue Trust (HART).\n" +
                "\n" +
                "We are grateful to the many individuals who have contributed to the setting up and running of Community Dog Welfare Kopan, especially those who sponsor the routine care of many of the dogs in our centre and to those kind people who have given some of our dogs a forever home.");

        mDogShelter.add("Motto:\n" +
                "\n" +
                "Let us work together to give all beings a better life.\n" +
                "\n" +
                "Vision:\n" +
                "\n" +
                "To bring about a time where there are no more suffering street dogs in Nepal.\n" +
                "\n" +
                "Mission:\n" +
                "\n" +
                "Street Dog Care aims to improve the health and living conditions of street dogs in Nepal, creating a healthier environment for all sentient beings.\n" +
                "\n" +
                "Over 25 000 stray dogs wander the streets of the Kathmandu valley. Most of these dogs live in a miserable state, suffering from disease, malnourishment and ill treatment.\n" +
                "\n" +
                "Street Dog Care was established in February 2009 at the Boudha Stupa. Every Saturday the SDC team, consisting of two local veterinarians and volunteers, treats street dogs for skin diseases, infections and injuries. Every dog is de-wormed and vaccinated against rabies. Dogs in serious condition are treated at a veterinary clinic and cared for during the week. In very critical conditions they are brought to the Dog Centre until they are fit to go back to the street or to be adopted.\n" +
                "\n" +
                "Since the Health Camps are held in public area, they have an important role in building community awareness and support. People are encouraged to bring sick and injured street dogs from their neighbourhoods for free treatment.\n" +
                "\n" +
                "Regular yearly rabies camps are performed.\n" +
                "\n" +
                "In 2011 we vaccinated 200 dogs against rabies,\n" +
                "\n" +
                "in 2012 we vaccinated 500 dogs against rabies,\n" +
                "\n" +
                "in 2013 we vaccinated 800 dogs against rabies,\n" +
                "\n" +
                "in 2014 we vaccinated 900 dogs against rabies,\n" +
                "\n" +
                "in 2015 we vaccinated 808 dogs against rabies, (this was the earthquake year)\n" +
                "\n" +
                "in 2016 we vaccinated 1000 dogs against rabies,\n" +
                "\n" +
                "in 2017 we vaccinated 1000 dogs against rabies,\n" +
                "\n" +
                "in 2018 we vaccinated 1000 dogs against rabies.");

    initRecyclerView();
    }


    private void initImages() {
        mImages.add("https://scontent.fktm3-1.fna.fbcdn.net/v/t1.0-9/24774785_1017044618444542_898799118556285614_n.png?_nc_cat=111&_nc_sid=85a577&_nc_ohc=PJAVtpSFWk4AX-QOKMf&_nc_ht=scontent.fktm3-1.fna&oh=1637c058fd15960dd1fbe5fb61a1cc83&oe=5EA43ED8");
        mNames.add("Sneha's Care Nepal");


        mImages.add("https://katcentre.org.np/wp-content/uploads/2019/12/KAT-5.png");
        mNames.add("Kathmandu Animal Treatment Centre");

        mImages.add("https://scontent.fktm3-1.fna.fbcdn.net/v/t1.0-9/60887976_1126231587578590_1114421653988704256_n.jpg?_nc_cat=103&_nc_sid=85a577&_nc_ohc=ePSgzu4IkxIAX8te1pa&_nc_ht=scontent.fktm3-1.fna&oh=601e8ec977bc1c2d4012933a226eeb5a&oe=5EA30ED4");
        mNames.add("Shree's Animal Rescue Nepal");

        mImages.add("https://communitydogwelfarekopan.org/wp-content/uploads/2019/05/CDW-logo-new.png");
        mNames.add("Community Dog Welfare Kopan");

        mImages.add("http://www.streetdogcare.org/wp-content/uploads/2019/01/banner-ok.png");
        mNames.add("Street Dog Care");

        initRecyclerView();
    }
    private void initRecyclerView() {
        Log.d(TAG,"initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.dogShelterRecycler);

        DogShelterAdapter adapter = new DogShelterAdapter(this,mNames,mImages, mDogShelter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
