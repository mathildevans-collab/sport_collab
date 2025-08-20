import { Injectable } from '@angular/core';
import { Seance } from '../models/seance';

@Injectable({
  providedIn: 'root'
})
export class SeanceService {
  // Simule un tableau de séances en mémoire
  private readonly seances: any[] = [];

  // Ajouter une nouvelle séance
  ajouterSeance(seance: Omit<Seance, 'id'>) {
    const seanceAvecId = { ...seance, id: this.nextId++ };
    this.seances.push(seanceAvecId);
  }

  // Retourner toutes les séances enregistrées
  getSeances() {
    return this.seances;
  }

  

  private nextId = 1;

  
}
