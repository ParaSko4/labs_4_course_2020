﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace client.WS {
    using System.Runtime.Serialization;
    using System;
    
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="Contacts", Namespace="http://tempuri.org/")]
    [System.SerializableAttribute()]
    public partial class Contacts : object, System.Runtime.Serialization.IExtensibleDataObject, System.ComponentModel.INotifyPropertyChanged {
        
        [System.NonSerializedAttribute()]
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        private int idField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string nameField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string numberField;

        public Contacts(string name, string number)
        {
            this.name = name;
            this.number = number;
        }
        
        [global::System.ComponentModel.BrowsableAttribute(false)]
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData {
            get {
                return this.extensionDataField;
            }
            set {
                this.extensionDataField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true)]
        public int id {
            get {
                return this.idField;
            }
            set {
                if ((this.idField.Equals(value) != true)) {
                    this.idField = value;
                    this.RaisePropertyChanged("id");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false)]
        public string name {
            get {
                return this.nameField;
            }
            set {
                if ((object.ReferenceEquals(this.nameField, value) != true)) {
                    this.nameField = value;
                    this.RaisePropertyChanged("name");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false)]
        public string number {
            get {
                return this.numberField;
            }
            set {
                if ((object.ReferenceEquals(this.numberField, value) != true)) {
                    this.numberField = value;
                    this.RaisePropertyChanged("number");
                }
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(ConfigurationName="WS.ContactsServiceSoap")]
    public interface ContactsServiceSoap {
        
        // CODEGEN: Generating message contract since element name GetDictResult from namespace http://tempuri.org/ is not marked nillable
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/GetDict", ReplyAction="*")]
        client.WS.GetDictResponse GetDict(client.WS.GetDictRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/GetDict", ReplyAction="*")]
        System.Threading.Tasks.Task<client.WS.GetDictResponse> GetDictAsync(client.WS.GetDictRequest request);
        
        // CODEGEN: Generating message contract since element name contact from namespace http://tempuri.org/ is not marked nillable
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/AddDict", ReplyAction="*")]
        client.WS.AddDictResponse AddDict(client.WS.AddDictRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/AddDict", ReplyAction="*")]
        System.Threading.Tasks.Task<client.WS.AddDictResponse> AddDictAsync(client.WS.AddDictRequest request);
        
        // CODEGEN: Generating message contract since element name contact from namespace http://tempuri.org/ is not marked nillable
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/UpdDict", ReplyAction="*")]
        client.WS.UpdDictResponse UpdDict(client.WS.UpdDictRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/UpdDict", ReplyAction="*")]
        System.Threading.Tasks.Task<client.WS.UpdDictResponse> UpdDictAsync(client.WS.UpdDictRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/DelDict", ReplyAction="*")]
        void DelDict(int id);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/DelDict", ReplyAction="*")]
        System.Threading.Tasks.Task DelDictAsync(int id);
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class GetDictRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="GetDict", Namespace="http://tempuri.org/", Order=0)]
        public client.WS.GetDictRequestBody Body;
        
        public GetDictRequest() {
        }
        
        public GetDictRequest(client.WS.GetDictRequestBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute()]
    public partial class GetDictRequestBody {
        
        public GetDictRequestBody() {
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class GetDictResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="GetDictResponse", Namespace="http://tempuri.org/", Order=0)]
        public client.WS.GetDictResponseBody Body;
        
        public GetDictResponse() {
        }
        
        public GetDictResponse(client.WS.GetDictResponseBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://tempuri.org/")]
    public partial class GetDictResponseBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public client.WS.Contacts[] GetDictResult;
        
        public GetDictResponseBody() {
        }
        
        public GetDictResponseBody(client.WS.Contacts[] GetDictResult) {
            this.GetDictResult = GetDictResult;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class AddDictRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="AddDict", Namespace="http://tempuri.org/", Order=0)]
        public client.WS.AddDictRequestBody Body;
        
        public AddDictRequest() {
        }
        
        public AddDictRequest(client.WS.AddDictRequestBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://tempuri.org/")]
    public partial class AddDictRequestBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public client.WS.Contacts contact;
        
        public AddDictRequestBody() {
        }
        
        public AddDictRequestBody(client.WS.Contacts contact) {
            this.contact = contact;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class AddDictResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="AddDictResponse", Namespace="http://tempuri.org/", Order=0)]
        public client.WS.AddDictResponseBody Body;
        
        public AddDictResponse() {
        }
        
        public AddDictResponse(client.WS.AddDictResponseBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute()]
    public partial class AddDictResponseBody {
        
        public AddDictResponseBody() {
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class UpdDictRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="UpdDict", Namespace="http://tempuri.org/", Order=0)]
        public client.WS.UpdDictRequestBody Body;
        
        public UpdDictRequest() {
        }
        
        public UpdDictRequest(client.WS.UpdDictRequestBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://tempuri.org/")]
    public partial class UpdDictRequestBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(Order=0)]
        public int id;
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=1)]
        public client.WS.Contacts contact;
        
        public UpdDictRequestBody() {
        }
        
        public UpdDictRequestBody(int id, client.WS.Contacts contact) {
            this.id = id;
            this.contact = contact;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class UpdDictResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="UpdDictResponse", Namespace="http://tempuri.org/", Order=0)]
        public client.WS.UpdDictResponseBody Body;
        
        public UpdDictResponse() {
        }
        
        public UpdDictResponse(client.WS.UpdDictResponseBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute()]
    public partial class UpdDictResponseBody {
        
        public UpdDictResponseBody() {
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface ContactsServiceSoapChannel : client.WS.ContactsServiceSoap, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class ContactsServiceSoapClient : System.ServiceModel.ClientBase<client.WS.ContactsServiceSoap>, client.WS.ContactsServiceSoap {
        
        public ContactsServiceSoapClient() {
        }
        
        public ContactsServiceSoapClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public ContactsServiceSoapClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public ContactsServiceSoapClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public ContactsServiceSoapClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        client.WS.GetDictResponse client.WS.ContactsServiceSoap.GetDict(client.WS.GetDictRequest request) {
            return base.Channel.GetDict(request);
        }
        
        public client.WS.Contacts[] GetDict() {
            client.WS.GetDictRequest inValue = new client.WS.GetDictRequest();
            inValue.Body = new client.WS.GetDictRequestBody();
            client.WS.GetDictResponse retVal = ((client.WS.ContactsServiceSoap)(this)).GetDict(inValue);
            return retVal.Body.GetDictResult;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<client.WS.GetDictResponse> client.WS.ContactsServiceSoap.GetDictAsync(client.WS.GetDictRequest request) {
            return base.Channel.GetDictAsync(request);
        }
        
        public System.Threading.Tasks.Task<client.WS.GetDictResponse> GetDictAsync() {
            client.WS.GetDictRequest inValue = new client.WS.GetDictRequest();
            inValue.Body = new client.WS.GetDictRequestBody();
            return ((client.WS.ContactsServiceSoap)(this)).GetDictAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        client.WS.AddDictResponse client.WS.ContactsServiceSoap.AddDict(client.WS.AddDictRequest request) {
            return base.Channel.AddDict(request);
        }
        
        public void AddDict(client.WS.Contacts contact) {
            client.WS.AddDictRequest inValue = new client.WS.AddDictRequest();
            inValue.Body = new client.WS.AddDictRequestBody();
            inValue.Body.contact = contact;
            client.WS.AddDictResponse retVal = ((client.WS.ContactsServiceSoap)(this)).AddDict(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<client.WS.AddDictResponse> client.WS.ContactsServiceSoap.AddDictAsync(client.WS.AddDictRequest request) {
            return base.Channel.AddDictAsync(request);
        }
        
        public System.Threading.Tasks.Task<client.WS.AddDictResponse> AddDictAsync(client.WS.Contacts contact) {
            client.WS.AddDictRequest inValue = new client.WS.AddDictRequest();
            inValue.Body = new client.WS.AddDictRequestBody();
            inValue.Body.contact = contact;
            return ((client.WS.ContactsServiceSoap)(this)).AddDictAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        client.WS.UpdDictResponse client.WS.ContactsServiceSoap.UpdDict(client.WS.UpdDictRequest request) {
            return base.Channel.UpdDict(request);
        }
        
        public void UpdDict(int id, client.WS.Contacts contact) {
            client.WS.UpdDictRequest inValue = new client.WS.UpdDictRequest();
            inValue.Body = new client.WS.UpdDictRequestBody();
            inValue.Body.id = id;
            inValue.Body.contact = contact;
            client.WS.UpdDictResponse retVal = ((client.WS.ContactsServiceSoap)(this)).UpdDict(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<client.WS.UpdDictResponse> client.WS.ContactsServiceSoap.UpdDictAsync(client.WS.UpdDictRequest request) {
            return base.Channel.UpdDictAsync(request);
        }
        
        public System.Threading.Tasks.Task<client.WS.UpdDictResponse> UpdDictAsync(int id, client.WS.Contacts contact) {
            client.WS.UpdDictRequest inValue = new client.WS.UpdDictRequest();
            inValue.Body = new client.WS.UpdDictRequestBody();
            inValue.Body.id = id;
            inValue.Body.contact = contact;
            return ((client.WS.ContactsServiceSoap)(this)).UpdDictAsync(inValue);
        }
        
        public void DelDict(int id) {
            base.Channel.DelDict(id);
        }
        
        public System.Threading.Tasks.Task DelDictAsync(int id) {
            return base.Channel.DelDictAsync(id);
        }
    }
}
