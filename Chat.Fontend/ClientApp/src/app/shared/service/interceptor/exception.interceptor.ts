import {
    HttpEvent,
    HttpHandler,
    HttpRequest,
    HttpErrorResponse,
    HttpInterceptor,
} from '@angular/common/http';
import { catchError} from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';


@Injectable({
    providedIn: 'root',
})
export class ExceptionInterceptor implements HttpInterceptor {
    intercept(
        request: HttpRequest<any>,
        next: HttpHandler
    ): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(
            catchError((error: HttpErrorResponse) => {
                if (error.status === 400) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: error.error.message
                      })
                }

                if (error.status === 401) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'unauthorized access error'
                      })
                }
                return throwError("");
            })
        );
    }
    
}
