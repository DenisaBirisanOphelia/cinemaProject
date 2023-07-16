import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {Client} from "../../model/Client";
import {MovieService} from "../../service/MovieService";
import {ClientService} from "../../service/ClientService";

@Component({
  selector: 'app-first-page',
  templateUrl: './AccountDialog.component.html',
  styleUrls: ['./AccountDialog.component.css']
})
export class AccountDialogComponent implements OnInit {
  clientList: Client[] = [];
  displayedColumns: string[] = ['id', 'nume', 'email', 'age', 'password', 'loggedClient'];

  constructor(private dialogRef: MatDialogRef<AccountDialogComponent>,
              private clientService: ClientService) { }

  ngOnInit(): void {
    this.clientService.findLoggedClients().subscribe((res:any) => {
        console.log(res);
        this.clientList = res;
      },
      (_error) => {

      });
  }


      close()
    {
      this.dialogRef.close();
    }

}
