import { User } from "./user.model";

export class Entreprise {
    id!: string;
    nom!: string;
    adresse!: string;
    secteuractivite!: string;
    Matricule!: string; 
    ville!: string;
    siegesociale!: string;
    codeTVA!: string;
    logo: any; 
    users!: User[];
}