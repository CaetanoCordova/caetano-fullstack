import { createSlice, type PayloadAction } from "@reduxjs/toolkit"

interface Itens{
    titulo: string
    descricao: string
    valor: number;
    dataVencimento: Date;
}

interface contaState{
    itens: Itens[],
}

const inicialState: contaState = {
    itens: []
}

const contaSlice = createSlice({
    name :'conta',
    initialState: inicialState,
    reducers:{
        adicionarConta : (state, action : PayloadAction<{item: Itens}>) =>{
            state.itens.push(action.payload.item);
            
        }
    }
});

export const {adicionarConta} = contaSlice.actions;
export default contaSlice.reducer;