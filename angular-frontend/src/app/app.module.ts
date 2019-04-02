import { BrowserModule, BrowserTransferStateModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import {A11yModule} from '@angular/cdk/a11y';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {ScrollingModule} from '@angular/cdk/scrolling';
import {CdkStepperModule} from '@angular/cdk/stepper';
import { FileDropModule } from 'ngx-file-drop';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { NabBarComponent } from './nab-bar/nab-bar.component';
import { ZombotronComponent } from './zombotron/zombotron.component';
import { FooterComponent } from './footer/footer.component';
import { ConceptsComponent } from './concepts/concepts.component';
import { DeveloperTeamComponent } from './developer-team/developer-team.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { ResultsComponent } from './results/results.component';
import {FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ResultcardComponent } from './resultcard/resultcard.component';
import { SocketService } from './services/socket.service';
import {MatButtonModule, MatButton} from '@angular/material/button';
import { SearchinfoService } from './services/searchinfo.service';
// import { UploadService } from './services/upload.service';
import { SessionId } from './domain/sessionId';
import { DataService } from './domain/data-service';
import { HttpClientModule } from '@angular/common/http';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { TokenService } from './services/token.service';
import { AuthService } from './services/auth.service';
import { AuthInterceptor } from './services/auth-interceptor';
import { LoginComponent } from './login/login.component';
import { UploadComponent } from './upload/upload.component';

import {
  MatAutocompleteModule,
  MatBadgeModule,
  MatBottomSheetModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatDividerModule,
  MatExpansionModule,
  MatGridListModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatStepperModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatTreeModule,
  MatInputModule,
  MatIconModule,
  MatSelectModule,
  
} from '@angular/material';
import { DisplayAdminHomeComponent } from './display-admin-home/display-admin-home.component';
import { QuestionClassifyComponent } from './question-classify/question-classify.component';
import { DomainConceptComponent } from './domain-concept/domain-concept.component';
import {NodeComponent } from './neo4j-node/neo4j-node.component';
import { TermComponent } from './neo4j-term/neo4j-term.component';
import { ChatComponent } from './chat/chat.component';
import { D3Component } from './d3/d3.component';
import { GraphComponent } from './d3/visuals/graph/graph.component';
import { SHARED_VISUALS } from './d3/visuals/shared';
import { D3_DIRECTIVES, D3Service } from './d3/d3';
import { ShowNodesComponent } from './show-nodes/show-nodes.component';
import { DisplayD3Component } from './display-d3/display-d3.component';
import { Test } from './test';
import { Intent } from './testIntent';
import { TestIntent } from './intentArray';
import { Testconcept } from './conceptArray';
import { D3Nodes } from './domain/d3Nodes';
import { RecommendationcardComponent } from './recommendationcard/recommendationcard.component';
import { TopsearchComponent } from './topsearch/topsearch.component';
import { ToptrendingComponent } from './toptrending/toptrending.component';
import { WebresultcardComponent } from './webresultcard/webresultcard.component';
import { DisplayIntentComponent } from './display-intent/display-intent.component';
import { NavigConceptComponent } from './navig-concept/navig-concept.component';
import { NavigIntentComponent } from './navig-intent/navig-intent.component';
import { ConceptNodesComponent } from './concept-nodes/concept-nodes.component';
import { IntentNodesComponent } from './intent-nodes/intent-nodes.component';
@NgModule({
  declarations: [
    AppComponent,
    AboutUsComponent,
    NabBarComponent,
    ZombotronComponent,
    FooterComponent,
    ConceptsComponent,
    DeveloperTeamComponent,
    ContactUsComponent,
    SearchBarComponent,
    HomeComponent,
    ResultsComponent,
    ResultcardComponent,
    AdminHomeComponent,
    LoginComponent,
    DisplayAdminHomeComponent,
    QuestionClassifyComponent,
    DomainConceptComponent,
    UploadComponent,
    NodeComponent,
    TermComponent,
    ChatComponent,
    D3Component,
    AppComponent,
    GraphComponent,
    ...SHARED_VISUALS,
    ...D3_DIRECTIVES,
    ShowNodesComponent,
    DisplayD3Component,
    RecommendationcardComponent,
    TopsearchComponent,
    ToptrendingComponent,
    WebresultcardComponent,
    DisplayIntentComponent,
    NavigConceptComponent,
    NavigIntentComponent,
    ConceptNodesComponent,
    IntentNodesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MatAutocompleteModule,
    MatButtonModule,
    DragDropModule,
  MatBadgeModule,
  MatButtonModule,
  MatBottomSheetModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatDividerModule,
  MatExpansionModule,
  MatGridListModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatStepperModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatTreeModule,
  BrowserModule,
  AppRoutingModule,
  BrowserAnimationsModule,
  MatInputModule,
  MatIconModule,
  MatButtonModule,
  MatSelectModule,
  HttpClientModule,
  ReactiveFormsModule,
  FileDropModule,
  FormsModule,
  BrowserModule.withServerTransition({ appId: 'serverApp' }),
  BrowserTransferStateModule,
  ],
  providers: [SocketService,SessionId,DataService,TokenService,AuthService,AuthInterceptor,D3Service,Test,D3Nodes,Intent,TestIntent,Testconcept],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  bootstrap: [AppComponent],
  entryComponents:[TermComponent,ChatComponent]
})
export class AppModule { }
