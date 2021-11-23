import React from "react";
import NavbarComponent from "../components/navbar";
import SubNavbarComponent from "../components/sub-navbar";
import TableComponent from "../components/table";
import { GlobalStyle } from "./global-style";
import { AppContainer, PageContent } from "./styled-components";

export default function AppComponent() {
  return (
    <>
      <GlobalStyle />
      <AppContainer>
        <NavbarComponent />
        <SubNavbarComponent />
        <PageContent>
          <TableComponent />
        </PageContent>
      </AppContainer>
    </>
  );
}
