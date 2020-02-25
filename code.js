export default class App extends Component{constructor(props) {
  super(props);
  this.state = { isOn: false };
  this.updateStatus();
}
turnOn = () => {
  NativeModules.Bulb.turnOn();
  this.updateStatus()}
  turnOff = () => {
    NativeModules.Bulb.turnOff();
    this.updateStatus()
  }
  updateStatus = () => {
    NativeModules.Bulb.getStatus( (error, isOn)=>{
      this.setState({
        isOn: isOn});
      }
    )}
    render() {
      return (
        <View style={styles.container}>
          <Text style={styles.welcome}>Welcome to Light App!!</Text>
          <Text> Bulb is {this.state.isOn ? "ON": "OFF"}</Text>
          {!this.state.isOn ?
            <ButtononPress={this.turnOn}title="Turn ON "color="#FF6347"/>
            :<ButtononPress={this.turnOff}title="Turn OFF "color="#FF6347"/> }
        </View>
      );
    }
  }
    const styles = StyleSheet.create({container: {flex: 1,justifyContent: 'center',alignItems: 'center',backgroundColor: '#F5FCFF',},});
