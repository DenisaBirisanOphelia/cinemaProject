import { Component, OnInit } from '@angular/core';
import {Form, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Client} from "../../model/Client";
import {ClientService} from "../../service/ClientService";
import {ActivatedRoute, Router} from "@angular/router";
import {EmailService1} from "../../service/EmailService1";


@Component({
  selector: 'app-first-page',
  templateUrl: './email.component.html'
  //styleUrls: ['./first-page.component.css']
})
export class EmailComponent implements OnInit {

  FormData: FormGroup|undefined;
  EmailAddress:string='';
  Body:string='';

  constructor(private emailService:EmailService1,
              private builder:FormBuilder,
              private route:ActivatedRoute,
              public router: Router) { }

  ngOnInit(): void {
    this.FormData =this.builder.group({
      EmailAddress:['', Validators.required],
      Body:['',Validators.required],
    })
  }

  onSubmit(form: FormGroup) {
    const input = {
      to: form.value.EmailAddress,
      subject: 'Email from Angular app',
      html: form.value.Body
    };

    this.emailService.SendEmail(input)
      .subscribe(() => {
        console.log("success");
        location.href = 'https://mailthis.to/confirm';

      })
  }
/**/
}
