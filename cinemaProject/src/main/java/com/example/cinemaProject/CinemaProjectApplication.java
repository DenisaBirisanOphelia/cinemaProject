package com.example.cinemaProject;

import com.example.cinemaProject.model.*;
import com.example.cinemaProject.repository.*;
import com.example.cinemaProject.service.Implementare.*;
import com.sun.tools.javac.Main;
import org.hibernate.boot.model.source.spi.SingularAttributeSourceToOne;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
@EntityScan
@EnableJpaRepositories
public class CinemaProjectApplication  {
	public static void main(String[] args) {
		SpringApplication.run(CinemaProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository,
						   MovieRepository movieRepository,
						   ReviewRepository reviewRepository,
						   WatchListRepository watchListRepository,
						   ClientRepository clientRepository,
						   BiletRepository biletRepository,
						   CosCumparaturiRepository cosCumparaturiRepository) {
		return args -> {

   //ZONA UNDE IMI CREEZ OBIECTE PENTRU A PUTEA TESTA OPERATIILE
			//LocatieFizica locatie1=new LocatieFizica(1L,"Cluj-Napoca","Ceahlau",72,
				//	null,null);
//			//Admin admin1=new Admin(null,null);
//			Movie m=new Movie();
//			m.setName("Coherence");
//			Movie m2=new Movie();
//			m2.setName("Parasite");
//			m2.setRegizor("Allain Deloin");
//			m.setRegizor("Scorsese");
//
//			Movie m3=new Movie();
//			m3.setName("The wolf of wall street");
//			m3.setRegizor("Tommy Hilfiger");


			//PT TABELUL MOVIE
//			MovieServiceImplementare movieServiceImplementare=new
//					MovieServiceImplementare(movieRepository);
//			movieServiceImplementare.saveMovie(m3);
//			movieServiceImplementare.saveMovie(m);
//			movieServiceImplementare.saveMovie(m2);
			//System.out.println(movieRepository.findFirstByName("Coherence").getName());
			//movieServiceImplementare.updateMovie(m,"Connahan");
			//movieServiceImplementare.deleteMovie("Parasite");
/*
			//PT TABELUL REVIEW
			ReviewServiceImplementare reviewServiceImplementare=new
					ReviewServiceImplementare(reviewRepository);
            Review rev1=new Review();
			rev1.setComentariu("A fost oki");
			rev1.setFilmReviewed(m);
			rev1.setStele(3);
			Review rev3=new Review();
			rev3.setComentariu("Nu am pareri, why would would I? You're not paying us!");
			rev3.setFilmReviewed(m);
			rev3.setStele(3);
			//review-ul 2 e legat de filmul Paraste si e sters automat cand sterg si parasite
			Review rev2=new Review();
			rev2.setComentariu("Deloc nu mi-a placut:(");
			rev2.setFilmReviewed(m2);
			rev2.setStele(5);
			reviewServiceImplementare.saveReview(rev1);
			reviewServiceImplementare.saveReview(rev2);
			reviewServiceImplementare.saveReview(rev3);
			reviewServiceImplementare.findByStele(3).forEach(review ->
					System.out.println(review.getComentariu()));
			reviewServiceImplementare.updateMovie(rev1,"GENIAL!!10/10 would recommend!<3");
			//aici pot modifica si cu 3 sa se vad ca poate sterge mai multe deodata
			//asa nu mi le poate sterge pt ca movie e owner si persista=>fac cu remove
			//reviewServiceImplementare.deleteReview(5);
			movieServiceImplementare.deleteMovie("Parasite");

			//PT TABELUL USER(ACUTALLY CLINET+ADMIN)
			UserServiceImplementare userServiceImplementare=new UserServiceImplementare(
					userRepository);
			Client client1 = new Client();
			client1.setNume("Birisan");
			client1.setPrenume("Denisa");
			client1.setAge(22);
			Client client2=new Client();
			client2.setNume("Popescu");
			client2.setPrenume("Andrei");
			client2.setAge(27);
			//mi le salveaza direct in tabelul de Client(which is rlly cool btw)
			userServiceImplementare.saveUser((User) client1);
			userServiceImplementare.saveUser(client2);
			userServiceImplementare.updateUser(client1,26);
			userServiceImplementare.findByAge(26).stream().forEach(user->
					System.out.println(user.getNume()+" "+user.getPrenume()+" "+user.getAge()));
           userServiceImplementare.deleteUser(client2);

//			//PT TABELUL WATCHLIST

			WatchList watchList=new WatchList();
			WatchListImplementare watchListImplementare=new WatchListImplementare(
					watchListRepository);
			//prima var cd adaug inainte de a-l salva in repo
			Set<Movie> lista=new HashSet<>();
			Movie m3=new Movie();
			m3.setName("Shutter Island");
			Movie m4=new Movie();
			m4.setName("The Wolf of Wall Street");

			//POSIBIL CAND E CU CASCADETYPE.ALL SA SALVEZE PARINTELE SI E ALL GOOD SI PT COPIL
			//meaning pt mn=>cd vr sa creez un watchlist,adaug filmele ca si apartinand
			//wathclistului, si se salveaza automat in tabelul de watchlist, nu mai trebuie
			//sa creez eu intrare in watchlist cu filmele resp,deci chiar intuitiv, cum e si irl
			//aici nuj daca ebn, dar daca am un film fara watchlis,til ia ca avand watchlistu null

			movieServiceImplementare.saveMovie(m3);
			movieServiceImplementare.saveMovie(m4);
			watchList.setNumeWatchList("First WatchList");
			movieServiceImplementare.updateMovieWatchList(m,watchList);
			WatchList watchList1=new WatchList();
			watchList1.setNumeWatchList("Second watchList");
			movieServiceImplementare.updateMovieWatchList(m,watchList1);
			movieServiceImplementare.updateMovieWatchList(m3,watchList1);
			movieServiceImplementare.updateMovieWatchList(m4,watchList1);
			movieServiceImplementare.deleteMovie(m.getName());

			//deci intrarile is bune in tabele,dar 2 chestii mai verific
			//1.gasesc watchlist-u care contine un anumit movie
			  //cand il gaseste,afiseaza,cand nu, nu va afisa nimicpe ecran
			System.out.println("AICI AFISEZ WATCHLISTS  CARE CONTIN UN ANUME FILM");
             watchListImplementare.findWatchListContainingMovie(m3).forEach(w->
					 System.out.println(w.getNumeWatchList()));

			//2.afisez toate movies dintr-un watchlist(asta merge perf)
			///cumva asa, imi asigneaza toate movies fara watchclis la "watchlistu null", care e
			//default
			System.out.println("AICI AFISEZ TOATE FILMELE DIN TOATE WATCHLISTS PE RAND");
			watchListRepository.findAll().forEach(w->
			{
				System.out.print(w.getNumeWatchList()+" ");
				w.getListaFilmeDeVazut().stream().forEach(movie->
						System.out.println(movie.getName()));

			});

          //acuma legatura cu tabelul de client= > intre client si movie doar,
			//ca leg intre movie si wacthlist se face automat
			ClientServiceImplementare clientServiceImplementare=new ClientServiceImplementare(
					clientRepository);

			clientServiceImplementare.saveClient(client1);

            clientServiceImplementare.updateClientAddMovieToListaMoviesCurente(client1,m3);

			//aici verific clientul Denisa are legatura cu movie m3
			System.out.println("AICI VERIFIC DACA client1=Denisa detine filmul m3=Shutter Island");
			clientServiceImplementare.findByName(client1.getNume(),client1.getPrenume())
							.getMovies().forEach(movie->
							System.out.println(movie.getName()));

              //deci trebe sa verific daca clientu pt movie m3 chiar e client1, adica Denisa
			System.out.println("AICI VERIFIC DACA FILMUL m3=Shutter Island chiar are ca si" +
					" client pe Denisa");
			movieServiceImplementare.findByName("Shutter Island").getClienti().
					forEach(client->
			{
				System.out.println(client.getNume()+" "+client.getPrenume());
			});

            //PT TABELUL DE BILET
			BiletServiceImplementare biletServiceImplementare=new BiletServiceImplementare(
					biletRepository);
			//pretul si locul pt Bilet vor veni din interfata
			//trebe sa propag din movies in tabelul de Bilet toate modificarile neaparat
			//=>Movies e owning side
			Movie m5=new Movie();
			m5.setName("Lala band the movie");
			movieServiceImplementare.saveMovie(m5);
			//acum operatiile pe biletul  respectiv
			Bilet biletDeModificat=biletServiceImplementare.findBiletByNamePriceLoc(m5,0,0);
			//asta va trebui tot din repo-ul pt movie repo modificata ca sa se propage frumos
			movieServiceImplementare.updateMovieBiletPretAndLoc(m5,45,113);
			//movieServiceImplementare.deleteBiletForMovie(m5);


			//PT TABELUL DE COS DE CUMPARATURI
			CosCumparaturiServiceImplementare cosCumparaturiServiceImplementare=
					new CosCumparaturiServiceImplementare(cosCumparaturiRepository);
			//asta e finally un tabel pe care il fac normalcu operatii hopefully
			//si aici trebe sa se propage modificarile de loc si bani pt Bilet
			//deci Bilet trebe sa fie OWNING SIDE
			CosCumparaturi cosCumparaturi=new CosCumparaturi();
           // cosCumparaturiServiceImplementare.saveCosCumparaturi(cosCumparaturi);
			Bilet biletDeModificat1=biletServiceImplementare.findBiletByNamePriceLoc(m5,45,113);
			cosCumparaturiServiceImplementare.addBiletToCosCumparaturi(null);
			//cosCumparaturiServiceImplementare.removeBletFromCosCumparaturi(biletDeModificat1);
			//sunt mai multe instante incat adminu sa vada ce are fiecare client in cosul sau

			System.out.println("Bilete sub 90 de lei:");
			cosCumparaturiServiceImplementare.findBileteUnderASUm(90).forEach(bilet->
					System.out.println("Pretul pentru biletul la filmul '"+bilet.getFilmDinBilet().
							getName()+ "' este: "+bilet.getPret()));
*/
			//PT TABELUL ADMIN(celelalte op funct exact la fel ca pt client, fiind cu
			// aceeasi impl de user), aici am vrut doar sa arat ca merge okay si insereaza adminu
			//unde trebuie
			UserServiceImplementare userServiceImplementare=new UserServiceImplementare(userRepository);
			Client client1=new Client();
			client1.setNume("Kio");
			client1.setPrenume("Mirel");
			client1.setPassword("emilia");
			client1.setEmail("a");
			client1.setAge(23);
			client1.setConfirmedClient(true);
			client1.setLoggedClient(true);
			userServiceImplementare.saveUser(client1);
			ClientServiceImplementare clientServiceImplementare=new ClientServiceImplementare(clientRepository);
			Client da=clientRepository.findFirstByNumeAndPrenume("Kio","Mirel");
			System.out.println(da.getNume());
			clientServiceImplementare.findByNumePrenume("Kio","Mirel");

			System.out.println(da.getNume());


			Admin a=new Admin();
			a.setNume("d");
			a.setPrenume("b");
			a.setPassword("ghici");
			a.setAge(22);
			a.setEmail("denisaopehali2000@yahoo.com");
			userServiceImplementare.saveUser(a);


//			//am creat un bilet
//			Bilet b=new Bilet();
//			b.setLocInSala("1a");
//			b.setPret(999);
//
//			biletRepository.save(b);
//
			CosCumparaturiServiceImplementare cosCumparaturiServiceImplementare=
					new CosCumparaturiServiceImplementare(cosCumparaturiRepository,clientRepository,
							biletRepository);
//
//			cosCumparaturiServiceImplementare.addBiletToCosCumparaturi(b.getId(),"Kio","Mirel");
//
//       Bilet b =new Bilet();
//	   b.setLocInSala("1A");
//
//			Bilet b1 =new Bilet();
//			b1.setLocInSala("1B");
//
//
//			b.setFilmDinBilet(m);
//			b1.setFilmDinBilet(m);
//			biletRepository.save(b);
//			biletRepository.save(b1);

			//cosCumparaturiServiceImplementare.addBiletToCosCumparaturi(b.getId(),"Kio","Mirel");
			//cosCumparaturiServiceImplementare.addBiletToCosCumparaturi(b1.getId(),"Kio","Mirel");

		};
	}
}







