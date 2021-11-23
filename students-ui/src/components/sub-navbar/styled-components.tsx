import styled from "styled-components";

export const SubNavbarConatiner = styled.div`
  width: 100%;
  height: 30px;
  background-color: #dddddd;
  display: flex;
  flex-direction: row;
  
`;

interface ITab {
  active: boolean;
}

export const Tab = styled.div<ITab>`
  cursor: default;
  user-select: none;
  display: grid;
  place-items: center;
  padding: 0px 20px;
  height: 100%;
  font-size: 14px;
  background-color: #dddddd;
  filter: ${(props: ITab) => props.active ? 'brightness(.95)' : 'brightness(1.00)'};
  border-bottom: ${(props: ITab) => props.active ? '2px solid #ffb429' : '0px'};
  &:hover {
    filter: brightness(.95);
  }
  &:active {
    filter: brightness(.90);
  }
`;
