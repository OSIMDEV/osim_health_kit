enum ProviderType {
  huawei._("huawei"),
  samsung._("samsung");

  const ProviderType._(this._type);

  String get type => _type;

  final String _type;
}
