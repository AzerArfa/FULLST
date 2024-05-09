import { Component, OnInit } from '@angular/core';
import { User } from '../model/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users: User[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.listeUsers().subscribe(data => {
      this.users = data;
    });
  }

  getImage(img: any): string {
    return 'data:image/jpeg;base64,' + img;
  }

  loadUsers(): void {
    this.userService.listeUsers().subscribe((res) => {
      this.users = res.map(user => {
        console.log('User img:', user.img); // Debug log before modification
        if (user.img && !user.img.startsWith('data:image/jpeg;base64,')) {
          user.img = 'data:image/jpeg;base64,' + user.img;
        }
        console.log(user);
        console.log('Modified user img:', user.img); // Debug log after modification
        return user;
      });
    });
  }

  getRoleNames(user: User): string {
    return user.roles.map(role => role.name).join(', ');
  }

  onKeyUp(searchTerm: string): void {
    // Implement search functionality here
  }

  supprimerUser(userId: string): void {
    // Implement delete functionality here
  }
}
