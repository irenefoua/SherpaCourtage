package com.j3a.assurance.utilitaires;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.j3a.assurance.model.Physique;

public class ClientView {
private Physique client = new Physique();
private List<Physique> listClients = new ArrayList<Physique>();

public ClientView(){
	Physique c1 = new Physique();
	Calendar d1 = Calendar.getInstance();
	d1.set(1985, 6, 15);
	c1.setDateNaissPers(d1.getTime());
	c1.setNomRaisonSociale("KOUASSI");
	c1.setPrenomPers("FULBERT");
	c1.setProfession("Informaticien");
	c1.setLieuNaissPers("BOUAKE");
	c1.setSituationMatPers("celibataire");
	c1.setEmail("kouassi@gmail.com");
	c1.setNumPiecePers("C001278");
	c1.setTelephone("08450211");
	c1.setTypePiece("CNI");
	c1.setAdresse("02 BP 452 Bouake 02");
	getListClients().add(c1);
	
	Physique c2 = new Physique();
	Calendar d2 = Calendar.getInstance();
	d2.set(1970, 2, 12);
	c2.setDateNaissPers(d2.getTime());
	c2.setNomRaisonSociale("AKA");
	c2.setPrenomPers("BERNARD");
	c2.setProfession("Informaticien");
	c2.setLieuNaissPers("ABIDJAN");
	c2.setSituationMatPers("celibataire");
	c2.setEmail("akaBernard@gmail.com");
	c2.setNumPiecePers("C004522");
	c2.setTelephone("05450211");
	c1.setTypePiece("CNI");
	c2.setAdresse("25 BP 11 Abidjan 25");
	getListClients().add(c2);
}
public Physique getClient() {
	return client;
}
public void setClient(Physique client) {
	this.client = client;
}
public List<Physique> getListClients() {
	return listClients;
}
public void setListClients(List<Physique> listClients) {
	this.listClients = listClients;
}

}
