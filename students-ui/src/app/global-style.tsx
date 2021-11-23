import { createGlobalStyle } from "styled-components";

export const GlobalStyle = createGlobalStyle`
  * {
    box-sizing: border-box;
  }
  html, body, #root {
    height: 100%;
    width: 100%;
    margin: 0;
    background-color: #eeeeee;
    font-family: Arial;
  }
`;