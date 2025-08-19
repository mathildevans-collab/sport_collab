import { Injectable } from '@angular/core';

import { Seance } from '../models/seance';


@Injectable({
  providedIn: 'root'
})
export class SeanceService {

  private seances: Seance[] = [
    {
      id: 1,
      titre: 'Séance cardio',
      description: 'Course à pied et vélo',
      duree: 45,
      exercices: [
        { nom: 'Course à pied', repetitions: 1, series: 1 },
        { nom: 'Vélo', repetitions: 1, series: 1 }
      ]
    }
  ];

  getSeances(): Seance[] {
    return this.seances;
  }

  getSeanceParId(id: number): Seance | undefined {
    return this.seances.find(s => s.id === id);
  }

  ajouterSeance(seance: Seance) {
    this.seances.push(seance);
  }

  modifierSeance(seanceModifiee: Seance) {
    const index = this.seances.findIndex(s => s.id === seanceModifiee.id);
    if (index !== -1) {
      this.seances[index] = seanceModifiee;
    }
  }
}
