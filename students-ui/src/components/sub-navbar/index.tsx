import React from "react";
import { SubNavbarConatiner, Tab } from "./styled-components";

export default function SubNavbarComponent() {
  return (
    <SubNavbarConatiner>
      <Tab active={true}>Table</Tab>
      <Tab active={false}>Details</Tab>
      <Tab active={false}>Edit</Tab>
      <Tab active={false}>Work Orders</Tab>
    </SubNavbarConatiner>
  );
}
