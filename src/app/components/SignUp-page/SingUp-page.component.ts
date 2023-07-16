import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Movie} from "../../model/Movie";
import {ClientService} from "../../service/ClientService";
import {ActivatedRoute, Router} from "@angular/router";
import {EmailService1} from "../../service/EmailService1";

function emailValidator(control:any) {
  // regular expression to validate email format
  const EMAIL_REGEXP = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

  console.log(EMAIL_REGEXP.test(control.value) )
  return EMAIL_REGEXP.test(control.value) ? false : { invalidEmail: true };
}
function repPassValidator(control: AbstractControl)
{
  const password = control.parent?.get('password')?.value;
  const repeatPassword = control.value;
  return password === repeatPassword ? null : { invalidRepPass: true };
}


@Component({
  selector: 'app-first-page',
  templateUrl: './SingUp-page.component.html',
  styleUrls: ['./SingUp-page.component.css']
})
export class SignUpPageComponent implements OnInit {
  nume:string="";
  prenume:string="";
  password:string="";
  repeatPassword:string="";
  email:string="";
  signupForm:FormGroup | undefined;

  constructor(private clientService:ClientService,
              private emailService:EmailService1,
              private formBuilder:FormBuilder,
              private route:ActivatedRoute,
              public router: Router) { }

  ngOnInit(): void {
    this.createForm();
  }
createForm()
{
  //am creat form-ul
  this.signupForm=this.formBuilder.group({
    nume:[null, Validators.required],
    prenume:[null,Validators.required],
    password:[null,Validators.required],
    repeatPassword:[null,[Validators.required,repPassValidator]],
    email:[null,[Validators.required,emailValidator]]
  })
}
  signup(){
    //acum setez atributele pt client
    this.clientService.signup(this.signupForm?.value.nume,
      this.signupForm?.value.prenume,
      this.signupForm?.value.password,
      this.signupForm?.value.email).subscribe
    ((response) =>
      {console.log("signup successful");
       // this.router.navigate(['login'])
        const input = {
          to: this.signupForm?.value.email,
          subject: 'Email from Angular app',
          html: 'You have successfully registered!'
        };

        //setez ca a primit mailul de confirmare
        this.clientService.readyToGo(this.signupForm?.value.email,true);
        console.log(this.signupForm?.value.email)

       this.emailService.SendEmail(input)
          .subscribe(() => {
            console.log("success");
            location.href = 'https://mailthis.to/confirm';

          })

      },
      error => {
        console.log("unsuccessful signup");
  });


  }


}
