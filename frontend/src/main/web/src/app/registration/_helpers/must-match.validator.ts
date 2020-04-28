import {FormGroup} from '@angular/forms';

// custom validator to check that two fields match
export class MustMatchValidator {
  static passwordConfirming(form: FormGroup): { [key: string]: boolean} {
    const control = form.controls.password;
    const matchingControl = form.controls.confirmPassword;
    if (control.value !== matchingControl.value) {
      matchingControl.setErrors({ mustMatch: true });
      return {mustMatch: true};
    }
    return null;
  }
}
